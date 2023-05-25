package kr.co.ejyang.main_api.controller;

import lombok.extern.slf4j.Slf4j;
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
public class FileOpenController {

    /*******************************************************************************************
     * 파일 조회 - 임시 URL 조회
     *******************************************************************************************/
    @GetMapping("/view/{fileUrl}")
    public ResponseEntity viewFile(@PathVariable String fileUrl) {

        // TODO ::: fileUrl > Redis > Value > Stroage > File > InputStreamResource
        // Temp URL 조회 ( = Redis Key )

//        File file = new File(fileDTO.getPath());
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(fileDTO.getFileName(), "UTF-8"));
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(file.length())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
        return null;
    }

}
