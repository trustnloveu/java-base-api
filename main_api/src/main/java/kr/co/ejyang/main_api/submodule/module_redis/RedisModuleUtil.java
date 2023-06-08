package kr.co.ejyang.main_api.submodule.module_redis;

import kr.co.ejyang.module_redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RedisModuleUtil {

    private final RedisUtil redisUtil;

    // 생성자
    public RedisModuleUtil(@Autowired RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }


    /*******************************************************************************************
     * Redis 갱신 - 유효시간 X
     *******************************************************************************************/
    public void setRedisData(String key, String value) {
        redisUtil.setRedisData(key, value);
    }

    /*******************************************************************************************
     * Redis 갱신 - 유효시간 O
     *******************************************************************************************/
    public void setRedisDataWithTTL(String key, String value) {
        redisUtil.setRedisDataWithTTL(key, value);
    }

    /*******************************************************************************************
     * 기존 등록 데이터에 유효시간 부여
     *******************************************************************************************/
    public void setRedisTTL(String key) {
        redisUtil.removeRedisData(key);
    }


    /*******************************************************************************************
     * Map 데이터 가져오기
     *******************************************************************************************/
    public Map<String, Object> getRedisDataMap(String key) {
        return redisUtil.getRedisDataMap(key);
    }

    /*******************************************************************************************
     * String 데이터 가져오기
     *******************************************************************************************/
    public String getRedisDataString(String key) {
        return redisUtil.getRedisDataString(key);
    }

    /*******************************************************************************************
     * List 데이터 가져오기
     *******************************************************************************************/
    public List<Map<String, Object>> getRedisDataList(String key) {
        return redisUtil.getRedisDataList(key);
    }

    /*******************************************************************************************
     * 데이터 삭제
     *******************************************************************************************/
    public void removeRedisData(String key) {
        redisUtil.removeRedisData(key);
    }

}
