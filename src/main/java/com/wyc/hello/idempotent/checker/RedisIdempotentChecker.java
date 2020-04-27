package com.wyc.hello.idempotent.checker;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * created on 2020-04-26 15:50
 *
 * @author WangYongcan
 */
public class RedisIdempotentChecker implements IdempotentChecker {
    private StringRedisTemplate redisTemplate;

    public RedisIdempotentChecker(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final String DEFAULT_VALUE = "1";

    @Override
    public Object checkRepeat(String idempotentKey) {
        return checkRepeat(idempotentKey, DEFAULT_VALUE, 0L);
    }

    @Override
    public Object checkRepeat(String idempotentKey, Long timeout) {
        return checkRepeat(idempotentKey, DEFAULT_VALUE, timeout);
    }

    @Override
    public Object checkRepeat(String idempotentKey, String value, Long timeout) {
        String key = "hello:idempotent:" + idempotentKey;
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.MILLISECONDS);
        if(Boolean.FALSE.equals(flag)) {
            return redisTemplate.opsForValue().get(key);
        }
        return null;
    }

}
