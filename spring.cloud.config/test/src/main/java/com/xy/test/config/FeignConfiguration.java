package com.xy.test.config;

import feign.Feign;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xy
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder().requestInterceptor(requestTemplate -> {
            Map<String, String> headers = getHeaders();
            for (String headerName : headers.keySet()) {
                requestTemplate.header(headerName, headers.get(headerName));
            }
        });
    }

    private Map<String, String> getHeaders() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(1000, 1000);
    }
}
