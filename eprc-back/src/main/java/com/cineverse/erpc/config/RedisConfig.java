package com.cineverse.erpc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    /**
     * Redis 연결을 위한 connection 생성
     *
     * @return RedisConnectionFactory
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    /**
     * Redis 데이터 처리를 위한 템플릿을 구성
     * 해당 구성된 RedisTemplate을 통해서 데이터 통신으로 처리되는 대한 직렬화 수행
     *
     * @return RedisTemplate<String, Object>
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        /* 1. Redis 연결 */
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        /* 2. Key-Value 형태로 직렬화 */
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        /* 3. Hash Key-Value 형태로 직렬화 수행 */
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());

        /* 4. 기본적으로 직렬화 수행 */
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());

        return redisTemplate;
    }

    /**
     * 리스트에 접근하여 다양한 연산 수행
     *
     * @return ListOperations<String, Object>
     */
    public ListOperations<String, Object> getListOperations() {
        return this.redisTemplate().opsForList();
    }

    /**
     * 단일 데이터에 접근하여 다양한 연산을 수행
     *
     * @return ValueOperations<String, Object>
     */
    public ValueOperations<String, Object> getValueOperations() {
        return this.redisTemplate().opsForValue();
    }

    /**
     * Redis 작업 중 등록, 수정, 삭제에 대해서 처리 및 예외처리 수행
     *
     * @param operation
     * @return
     */
    public int excuteOperation(Runnable operation) {
        try {
            operation.run();
            return 1;
        } catch (Exception e) {
            System.out.println("Redis 오류 발생 :: " + e.getMessage());
            return 0;
        }
    }
}
