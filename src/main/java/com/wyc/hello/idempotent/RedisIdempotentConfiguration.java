package com.wyc.hello.idempotent;

import com.wyc.hello.idempotent.aop.IdempotentAspect;
import com.wyc.hello.idempotent.checker.RedisIdempotentChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * created on 2020-04-26 16:37
 *
 * @author WangYongcan
 */
@Configuration
public class RedisIdempotentConfiguration {

    @Bean
    public RedisIdempotentChecker redisIdempotentChecker(StringRedisTemplate redisTemplate) {
        return new RedisIdempotentChecker(redisTemplate);
    }

    @Bean
    public IdempotentAspect idempotentAspect() {
        return new IdempotentAspect();
    }

}
