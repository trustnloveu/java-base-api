package kr.co.ejyang.main_api.service;

import kr.co.ejyang.main_api.dto.FileRedisDto;
import kr.co.ejyang.main_api.submodule.module_file.FileModuleUtil;
import kr.co.ejyang.main_api.submodule.module_redis.RedisModuleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class OpenFileService {

    private final FileModuleUtil fileModuleUtil;
    private final RedisModuleUtil redisModuleUtil;

    // 생성자
    OpenFileService(
            @Autowired FileModuleUtil fileModuleUtil,
            @Autowired RedisModuleUtil redisModuleUtil
    ) {
        this.fileModuleUtil = fileModuleUtil;
        this.redisModuleUtil = redisModuleUtil;
    }

    /*******************************************************************************************
     * 파일 조회
     *******************************************************************************************/
    public InputStreamResource getFileByTempUrl(String tempUrl) {
        // Redis 조회
        Map<String, Object> redisMap = redisModuleUtil.getRedisDataMap(tempUrl);

        // 객체 생성
        FileRedisDto redisDto = FileRedisDto.builder()
                .savePath(redisMap.get("savePath").toString())
                .saveName(redisMap.get("saveName").toString())
                .build();

        // 파일 조회
        return fileModuleUtil.downloadFile(redisDto.savePath, redisDto.saveName);
    }

}
