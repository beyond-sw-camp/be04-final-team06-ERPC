package com.cineverse.erpc.redis.service;

import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public interface RedisSingleService {
    /* Redis 단일 데이터 값 등록/삭제 */
    int setSingleData(String key, Object value);

    /* Redis 단일 데이터 값 등록/삭제 (duration 값이 존재하면 메모리 상 유효시간 지정) */
    int setSingleData(String key, Object value, Duration duration);

    /* Redis 키를 기반으로 단일 데이터 값 조회 */
    String getSingleData(String key);

    /* Redis 키를 기반으로 단일 데이터 값 삭제 */
    int deleteSingleData(String key);
}
