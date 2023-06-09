package kr.co.ejyang.main_api.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.ejyang.main_api.service.OpenFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/file")
public class OpenFileController {

    private final OpenFileService openFileService;

    /*******************************************************************************************
     * Private 파일 조회
     *******************************************************************************************/
    @GetMapping(value = "/view/private/{tempUrl}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity viewPrivateFile(@PathVariable String tempUrl) throws FileNotFoundException {
        return new ResponseEntity<>(openFileService.getFileByTempUrl(tempUrl), HttpStatus.OK);
    }

    /*******************************************************************************************
     * Public 파일 조회
     *******************************************************************************************/
    @GetMapping(value = "/view/**", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity viewPublicFile(HttpServletRequest request) {

        String savePath = request.getRequestURI().split("/view")[1];

        return new ResponseEntity<>(openFileService.getPublicFile(savePath), HttpStatus.OK);
    }

}
