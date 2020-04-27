package wyc.hello.idempotent.test;

import com.wyc.hello.idempotent.RedisIdempotentConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * created on 2020-04-26 16:52
 *
 * @author WangYongcan
 */
@SpringBootApplication(scanBasePackages = {"wyc.hello.idempotent.test"})
public class IdempotentTest {
    public static void main(String[] args) {
        SpringApplication.run(IdempotentTest.class, args);
    }

    /*@Bean
    public RedisIdempotentConfiguration redisIdempotentConfiguration() {
        return new RedisIdempotentConfiguration();
    }*/

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        JedisConnectionFactory conn = new JedisConnectionFactory();
        conn.setDatabase(0);
        conn.setHostName("127.0.0.1");
        conn.setPort(6379);
        conn.setPassword("");
        conn.afterPropertiesSet();
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(conn);
        stringRedisTemplate.afterPropertiesSet();
        return stringRedisTemplate;
    }
}
