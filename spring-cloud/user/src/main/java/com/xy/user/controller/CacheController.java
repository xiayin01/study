package com.xy.user.controller;

import com.xy.user.service.cache.CacheService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 缓存控制层
 */
@RestController
@RequestMapping("cache")
@AllArgsConstructor
public class CacheController {

    private final CacheService cacheService;

    /**
     * 获取缓存数据
     *
     * @param key key
     * @return 结果
     */
    @GetMapping("{key}")
    public String getCache(@PathVariable("key") String key) {
        return cacheService.getCacheData(key);
    }

    @PostMapping
    public void addCache(String value) {
        cacheService.cachePut(value);
    }

    /**
     * 删除缓存
     *
     * @param key key
     */
    @DeleteMapping
    public void cacheEvict(String key) {
        cacheService.cacheEvict(key);
    }
}
