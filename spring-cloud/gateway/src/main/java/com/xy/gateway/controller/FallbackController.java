package com.xy.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 网关层回滚类
 *
 * @author xy
 */
@RestController
@RequestMapping("fallback")
public class FallbackController {

    @GetMapping
    public Map<String, String> fallback() {
        Map<String, String> map = new HashMap<>(1);
        map.put("resultCode", "fail");
        map.put("resultMessage", "网关异常");
        return map;
    }
}
