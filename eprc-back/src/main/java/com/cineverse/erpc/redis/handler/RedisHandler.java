package com.cineverse.erpc.redis.handler;

import com.cineverse.erpc.config.RedisConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisHandler {
    private final RedisConfig redisConfig;

    /**
     * 리스트에 접근하여 다양한 연산 수행
     *
     * @return ListOperation<String, Object>
     */
    public ListOperations<String, Object> getListOperations() {
        return redisConfig.redisTemplate().opsForList();
    }

    /**
     * 단일 데이터에 접근하여 다양한 연산 수행
     *
     * @return ValueOperations<String, Object>
     */
    public ValueOperations<String, Object> getValueOperations() {
        return redisConfig.redisTemplate().opsForValue();
    }

    /**
     * Redis 작업 중 등록, 수정, 삭제에 대하여 처리 및 예외처리를 수행
     *
     * @Param operation
     * @return
     */
    public int excuteOperation(Runnable operation) {
        try {
            operation.run();
            return  1;
        } catch (Exception e) {
            System.out.println("Redis 오류 발생 :: " + e.getMessage());
            return 0;
        }
    }

}
