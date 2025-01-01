package com.cineverse.erpc.redis.controller;

import com.cineverse.erpc.redis.dto.RedisDto;
import com.cineverse.erpc.redis.service.RedisSingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/redis/singleData")
public class RedisSingleDataController {
    private final RedisSingleService redisSingleService;


    @Autowired
    public RedisSingleDataController(RedisSingleService redisSingleService) {
        this.redisSingleService = redisSingleService;
    }

    /**
     * Redis 키를 기반으로 단일 데이터의 값을 조회.
     *
     * @param redisDto
     * @return
     */
    @PostMapping("/getValue")
    public ResponseEntity<Object> getValue(@RequestBody RedisDto redisDto) {
        String result = redisSingleService.getSingleData(redisDto.getKey());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Redis 키를 기반으로 단일 데이터의 값을 조회합니다.
     *
     * @param redisDto
     * @return
     */
    @PostMapping("/getValue")
    public ResponseEntity<Object> getValue(@RequestBody RedisDto redisDto) {
        String result = redisSingleService.getSingleData(redisDto.getKey());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Redis 단일 데이터 값을 등록/수정합니다.(duration 값이 존재하면 메모리 상 유효시간을 지정합니다.)
     *
     * @param redisDto
     * @return
     */
    @PostMapping("/setValue")
    public ResponseEntity<Object> setValue(@RequestBody RedisDto redisDto) {
        int result = 0;
        if (redisDto.getDuration() == null) {
            result = redisSingleService.setSingleData(redisDto.getKey(), redisDto.getValue());
        } else {
            result = redisSingleService.setSingleData(redisDto.getKey(), redisDto.getValue(), redisDto.getDuration());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Redis 키를 기반으로 단일 데이터의 값을 삭제합니다.
     *
     * @param redisDto
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRow(@RequestBody RedisDto redisDto) {
        int result = redisSingleService.deleteSingleData(redisDto.getKey());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
