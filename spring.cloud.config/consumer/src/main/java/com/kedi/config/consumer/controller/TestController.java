package com.kedi.config.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author xy
 */
@RestController
@RefreshScope
@RequestMapping("test")
public class TestController {

    @Value("${test}")
    private String testValue;

    /**
     * 测试
     *
     * @return
     */
    @GetMapping
    public String test() {
        return testValue;
    }
}
