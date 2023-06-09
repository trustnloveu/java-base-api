package kr.co.ejyang.main_api.service;

import kr.co.ejyang.main_api.dto.FileRedisDto;
import kr.co.ejyang.main_api.submodule.module_file.FileModuleUtil;
import kr.co.ejyang.main_api.submodule.module_redis.RedisModuleUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenFileService {

    private final FileModuleUtil fileModuleUtil;
    private final RedisModuleUtil redisModuleUtil;

    /*******************************************************************************************
     * 파일 조회 ( Private + Temp URL )
     *  - Redis 조회 - 저장 경로
     *******************************************************************************************/
    public byte[] getFileByTempUrl(String tempUrl) throws FileNotFoundException {
        try {
            // Redis 조회
            Map<String, Object> redisMap = redisModuleUtil.getRedisDataMap(tempUrl);

            // 객체 생성
            FileRedisDto redisDto = FileRedisDto.builder()
                    .savePath(redisMap.get("savePath").toString())
                    .build();

            // 파일 조회
            return fileModuleUtil.getPrivateFile(redisDto.savePath);
        } catch (NullPointerException e) {
            throw new FileNotFoundException("파일을 조회할 수 없습니다. ( Redis )");
        }

    }

    /*******************************************************************************************
     * 파일 조회 ( Public )
     *******************************************************************************************/
    public byte[] getPublicFile(String savePath) {
        return fileModuleUtil.getPublicFile(savePath);
    }

}
