package kr.co.ejyang.main_api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import kr.co.ejyang.main_api.dto.FileParamDto;
import kr.co.ejyang.main_api.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    /*******************************************************************************************
     * 파일 조회 - 임시 URL 발급 ( Redis > Temp Url 등록 )
     *******************************************************************************************/
    @PostMapping("/generate-temp-url")
    public ResponseEntity generateFileTempUrl(
            @Valid
            @RequestBody FileParamDto.TempUrl param
    ) {
        return new ResponseEntity<>(fileService.updateFileTempUrlOnRedis(param.fullPath, param.fileName), HttpStatus.OK);
    }


    /*******************************************************************************************
     * 단일 파일 업로드 (1) - 파일 자체 이름으로 저장
     *******************************************************************************************/
    @PostMapping("/upload-file")
    public ResponseEntity uploadSingleFileWithoutName(
            @Valid
            @RequestPart(value = "param") FileParamDto.Upload param,
            @RequestParam(value = "file") MultipartFile file
    ) throws JsonProcessingException {
        return new ResponseEntity<>(fileService.uploadSingleFileWithoutName(param, file), HttpStatus.OK);
    }

    /*******************************************************************************************
     * 단일 파일 업로드 (2) - 사용자 지정 파일명으로 저장
     *******************************************************************************************/
    @PostMapping("/upload-named-file")
    public ResponseEntity uploadSingleFileWithName(
            @Valid
            @RequestPart(value = "param") FileParamDto.UploadWithName param,
            @RequestParam(value = "file") MultipartFile file
    ) throws JsonProcessingException {
        return new ResponseEntity<>(fileService.uploadSingleFileWithName(param, file), HttpStatus.OK);
    }

    /*******************************************************************************************
     * 복수 파일 업로드
     *******************************************************************************************/
    @PostMapping("/upload-files")
    public ResponseEntity uploadMultiFiles(
            @Valid
            @RequestPart(value = "param") FileParamDto.Upload param,
            @RequestParam(value = "files") MultipartFile[] files
    ) throws JsonProcessingException {
        return new ResponseEntity<>(fileService.uploadMultiFiles(param, files), HttpStatus.OK);
    }

    /*******************************************************************************************
     * 파일 삭제
     *******************************************************************************************/
    @PostMapping("/delete-file")
    public ResponseEntity deleteFile(
            @Valid
            @RequestBody FileParamDto.Delete param
    ) {
        fileService.deleteFile(param.fullPath);
        return new ResponseEntity<>("파일 삭제 성공", HttpStatus.OK);
    }

    /*******************************************************************************************
     * 파일 다운로드
     *******************************************************************************************/
    @PostMapping("/download-file")
    public ResponseEntity downloadFile(
            @Valid
            @RequestBody FileParamDto.Download param
    ) {
        // 파일 확장자 확인
        int dotIndex = param.fullPath.lastIndexOf(".");
        String extType = param.fullPath.substring(dotIndex);

        // 반환 Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Content-Disposition", "filename=" + param.fileName + extType);
        headers.add("Set-Cookie", "fileDownload=true; path=/");

        return new ResponseEntity<>(fileService.downloadFile(param.fullPath), headers, HttpStatus.OK);
    }

}