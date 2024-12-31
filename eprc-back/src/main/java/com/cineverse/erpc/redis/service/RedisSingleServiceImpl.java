package com.cineverse.erpc.redis.service;

import com.cineverse.erpc.config.RedisConfig;
import com.cineverse.erpc.redis.handler.RedisHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisSingleServiceImpl implements RedisSingleService{
    private final RedisHandler redisHandler;
    private final RedisConfig redisConfig;

    /**
     * Redis의 경우 각 명령이 원자적으로 실행돼 @Transaction 어노테이션이 필요 없지만
     * 여러개의 연산을 한 트랜잭션으로 묶는 경우 사용가능하다.
     */

    /**
     * Redis 단일 데이터 값 등록/수정
     *
     * @param key: redis key
     * @param value: redis value
     * @return 성공(1), 실패(0)
     */
    @Override
    public int setSingleData(String key, Object value) {
        return redisHandler.excuteOperation(() -> redisHandler.getValueOperations().set(key, value));
    }

    /**
     * Redis 단일 데이터 값을 등록/수정(duration 값이 존재하면 메모리 상 유효시간 지정)
     *
     * @param key      : redis key
     * @param value:   : redis value
     * @param duration : redis 값 메모리 상의 유효시간.
     * @return {int} 성공(1), 실패(0)
     */
    @Override
    public int setSingleData(String key, Object value, Duration duration) {
        return redisHandler.excuteOperation(() -> redisHandler.getValueOperations().set(key, value, duration));
    }

    /**
     * Redis 키를 기반으로 단일 데이터의 값을 조회
     *
     * @param key : redis key
     * @return {String} redis value 값 반환 or 미 존재시 null 반환
     */
    @Override
    public String getSingleData(String key) {
        if (redisHandler.getValueOperations().get(key) == null) {
            return "";
        }
        return String.valueOf(redisHandler.getValueOperations().get(key));
    }

    /**
     * Redis 키를 기반으로 단일 데이터의 값을 삭제
     *
     * @param key : redis key
     * @return {int} 성공(1), 실패(0)
     */
    @Override
    public int deleteSingleData(String key) {
        return redisHandler.excuteOperation(() -> redisConfig.redisTemplate().delete(key));
    }
}
