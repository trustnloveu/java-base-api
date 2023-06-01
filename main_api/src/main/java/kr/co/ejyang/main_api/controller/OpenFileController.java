package kr.co.ejyang.main_api.controller;

import kr.co.ejyang.main_api.service.OpenFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping("/open/file")
public class OpenFileController {

    private final OpenFileService openFileService;

    // 생성자
    OpenFileController(@Autowired OpenFileService openFileService) {
        this.openFileService = openFileService;
    }

    /*******************************************************************************************
     * 파일 조회 - 임시 URL 조회
     *******************************************************************************************/
    @GetMapping("/view/{tempUrl}")
    public ResponseEntity viewFile(@PathVariable String tempUrl) {

        // Temp URL 조회 > 파일 다운로드
        InputStreamResource resource = openFileService.getFileByTempUrl(tempUrl);

        // 반환 Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Content-Disposition", "filename=" + resource.getDescription());
        headers.add("Set-Cookie", "fileDownload=true; path=/");
        // headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
        // headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        // headers.add("Pragma", "no-cache");
        // headers.add("Expires", "0");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}
