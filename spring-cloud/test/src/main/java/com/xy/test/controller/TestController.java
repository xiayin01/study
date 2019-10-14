package com.xy.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author xy
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public String test() throws InterruptedException {
        Thread.sleep(5000);
        return "xiayin";
    }
}
