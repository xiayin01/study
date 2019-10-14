package com.xy.eureka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author xy
 */
@RestController
@RequestMapping
public class TestController {

    @GetMapping
    public String test() {
        return "gateway熔断";
    }
}
