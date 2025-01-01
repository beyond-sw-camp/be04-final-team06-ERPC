package com.cineverse.erpc.redis.dto;

import lombok.Data;

import java.time.Duration;

@Data
public class RedisDto {
    private String key;
    private Object value;
    private Duration duration;
}
