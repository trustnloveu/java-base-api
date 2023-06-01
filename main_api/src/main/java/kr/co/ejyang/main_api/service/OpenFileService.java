package kr.co.ejyang.main_api.service;

import kr.co.ejyang.main_api.dto.FileRedisDto;
import kr.co.ejyang.module_file.service.FileServiceImplForLocal;
import kr.co.ejyang.module_redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class OpenFileService {

    private final FileServiceImplForLocal fileService;
    private final RedisUtil redisUtil;

    // 생성자
    OpenFileService(
        @Autowired FileServiceImplForLocal fileService,
        @Autowired RedisUtil redisUtil
    ) {
        this.fileService = fileService;
        this.redisUtil = redisUtil;
    }

    /*******************************************************************************************
     * 파일 조회
     *******************************************************************************************/
    public InputStreamResource getFileByTempUrl(String tempUrl) {
        // Redis 조회
        Map<String, Object> redisMap = redisUtil.getRedisDataMap(tempUrl);

        // 객체 생성
        FileRedisDto redisDto = FileRedisDto.builder()
                .savePath(redisMap.get("savePath").toString())
                .saveName(redisMap.get("saveName").toString())
                .build();

        // 파일 조회
        return fileService.downloadFile(redisDto.savePath, redisDto.saveName);
    }

}
