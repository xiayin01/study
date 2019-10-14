package com.xy.test.config;

import com.xy.test.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring扩展配置测试
 *
 * @author xy
 */
@ComponentScan(basePackages = {"com.xy.test.ext"})
@Configuration
public class ExtConfig {

    @Bean
    public User user() {
        return new User();
    }

}
