package kr.co.ejyang.main_api.controller;

import jakarta.validation.Valid;
import kr.co.ejyang.main_api.dto.FileParamDto;
import kr.co.ejyang.main_api.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Validated
@RestController
@RequestMapping("/file")
public class MainController {

    private final MainService mainService;

    // 생성자
    MainController(@Autowired MainService mainService) {
        this.mainService = mainService;
    }


    /*******************************************************************************************
     * 파일 정보 가져오기
     *******************************************************************************************/
    @PostMapping("/get-file-info")
    public ResponseEntity getFileInfo(
            @RequestParam(value = "file") MultipartFile file
    ) {
        return new ResponseEntity<>(mainService.getFileInfo(file), HttpStatus.OK);
    }

    /*******************************************************************************************
     * 단일 파일 업로드 (1) - 파일 자체 이름으로 저장
     *******************************************************************************************/
    @PostMapping("/upload-file")
    public ResponseEntity uploadSingleFileWithoutName(
            @Valid
            @RequestPart(value = "param") FileParamDto.Upload param,
            @RequestParam(value = "file") MultipartFile file
    ) {
        return new ResponseEntity<>(mainService.uploadSingleFileWithoutName(param, file), HttpStatus.OK);
    }

    /*******************************************************************************************
     * 단일 파일 업로드 (2) - 사용자 지정 파일명으로 저장
     *******************************************************************************************/
    @PostMapping("/upload-named-file")
    public ResponseEntity uploadSingleFileWithName(
            @Valid
            @RequestPart(value = "param") FileParamDto.UploadWithName param,
            @RequestParam(value = "file") MultipartFile file
    ) {
        return new ResponseEntity<>(mainService.uploadSingleFileWithName(param, file), HttpStatus.OK);
    }

    /*******************************************************************************************
     * 복수 파일 업로드
     *******************************************************************************************/
    @PostMapping("/upload-files")
    public ResponseEntity uploadMultiFiles(
            @Valid
            @RequestPart(value = "param") FileParamDto.Upload param,
            @RequestParam(value = "files") MultipartFile[] files
    ) {
        return new ResponseEntity<>(mainService.uploadMultiFiles(param, files), HttpStatus.OK);
    }

    /*******************************************************************************************
     * 파일 삭제
     *******************************************************************************************/
    @DeleteMapping("/delete-file")
    public ResponseEntity deleteFile(
            @Valid
            @RequestParam FileParamDto.Delete param
    ) {
        mainService.deleteFile(param.savePath);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /*******************************************************************************************
     * 파일 다운로드
     *******************************************************************************************/
    @PostMapping("/download-file")
    public ResponseEntity downloadFile(
            @Valid
            @RequestBody FileParamDto.Download param
    ) {
        // 반환 Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Content-Disposition", "filename=" + param.fileName);
        headers.add("Set-Cookie", "fileDownload=true; path=/");
        // headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
        // headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        // headers.add("Pragma", "no-cache");
        // headers.add("Expires", "0");

        return new ResponseEntity<>(mainService.downloadFile(param.savePath), headers, HttpStatus.OK);
    }

}
