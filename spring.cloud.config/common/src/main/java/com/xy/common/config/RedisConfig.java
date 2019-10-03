package com.xy.common.config;

import com.xy.common.redis.RedisLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redis配置类
 *
 * @author xy
 */
@Configuration
public class RedisConfig {

    @Bean
    @ConditionalOnMissingBean(RedisLock.class)
    public RedisLock testRedisLock() {
        return new RedisLock();
    }

}
