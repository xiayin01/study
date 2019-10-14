package com.xy.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

/**
 * 配置文件读取
 *
 * @author xy
 */
@PropertySource("classpath:/test.properties")
@Configuration
public class ProfileConfig implements EmbeddedValueResolverAware {

    @Value("${one}")
    private String one;

    private StringValueResolver valueResolver;
    private String three;

    @Profile("test")
    @Bean("one")
    public void one() {
        System.out.println("----" + one);
    }

    @Profile("dev")
    @Bean("two")
    public String two(@Value("${two}") String two) {
        System.out.println("...." + two);
        return two;
    }

    @Profile("prod")
    @Bean("three")
    public void three() {
        System.out.println("----" + three);
    }


    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.valueResolver = resolver;
        three = valueResolver.resolveStringValue("${three}");
    }
}
