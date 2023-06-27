package kr.co.ejyang.main_api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import kr.co.ejyang.main_api.dto.FileParamDto;
import kr.co.ejyang.main_api.model.ApiResponse;
import kr.co.ejyang.main_api.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static kr.co.ejyang.main_api.model.ApiResponse.returnByApiResponse;

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
    public ResponseEntity<ApiResponse> generateFileTempUrl(
            @Valid
            @RequestBody FileParamDto.TempUrl param
    ) {
        return returnByApiResponse(fileService.updateFileTempUrlOnRedis(param.fullPath, param.fileName));
    }


    /*******************************************************************************************
     * 단일 파일 업로드 (1) - 파일 자체 이름으로 저장
     *******************************************************************************************/
    @PostMapping("/upload-file")
    public ResponseEntity<ApiResponse> uploadSingleFileWithoutName(
            @Valid
            @RequestPart(value = "param") FileParamDto.Upload param,
            @RequestParam(value = "file") MultipartFile file
    ) throws JsonProcessingException {
        return returnByApiResponse(fileService.uploadSingleFileWithoutName(param, file));
    }

    /*******************************************************************************************
     * 단일 파일 업로드 (2) - 사용자 지정 파일명으로 저장
     *******************************************************************************************/
    @PostMapping("/upload-named-file")
    public ResponseEntity<ApiResponse> uploadSingleFileWithName(
            @Valid
            @RequestPart(value = "param") FileParamDto.UploadWithName param,
            @RequestParam(value = "file") MultipartFile file
    ) throws JsonProcessingException {
        return returnByApiResponse(fileService.uploadSingleFileWithName(param, file));
    }

    /*******************************************************************************************
     * 복수 파일 업로드
     *******************************************************************************************/
    @PostMapping("/upload-files")
    public ResponseEntity<ApiResponse> uploadMultiFiles(
            @Valid
            @RequestPart(value = "param") FileParamDto.Upload param,
            @RequestParam(value = "files") MultipartFile[] files
    ) throws JsonProcessingException {
        return returnByApiResponse(fileService.uploadMultiFiles(param, files));
    }

    /*******************************************************************************************
     * 파일 삭제
     *******************************************************************************************/
    @PostMapping("/delete-file")
    public ResponseEntity<ApiResponse> deleteFile(
            @Valid
            @RequestBody FileParamDto.Delete param
    ) {
        return returnByApiResponse(fileService.deleteFile(param.fullPath));
    }

    /*******************************************************************************************
     * 파일 다운로드
     *******************************************************************************************/
    @PostMapping("/download-file")
    public ResponseEntity<ApiResponse> downloadFile(
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

        return returnByApiResponse(headers, fileService.downloadFile(param.fullPath));
    }

}