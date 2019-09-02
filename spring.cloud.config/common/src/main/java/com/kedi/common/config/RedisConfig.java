package com.kedi.common.config;

import com.kedi.common.redis.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author xy
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisLock redisLock(){
        return new RedisLock();
    }
}
