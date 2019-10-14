package com.xy.test.config;

import com.xy.test.aop.LogAspect;
import com.xy.test.business.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 切面配置测试
 *
 * @author xy
 *
 */
@EnableAspectJAutoProxy
@Configuration
public class AopConfig {

    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
