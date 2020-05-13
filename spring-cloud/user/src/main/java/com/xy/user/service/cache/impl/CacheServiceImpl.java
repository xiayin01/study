package com.xy.user.service.cache.impl;

import com.xy.user.service.cache.CacheService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {

    @Override
    @Cacheable("cache")
    public String getCacheData(String key) {
        System.out.println("-----非缓存数据-------");
        return key;
    }

    @Override
    @CachePut("cache")
    public void cachePut(String value) {
        System.out.println("-----cachePut-------");
    }

    @Override
    @CacheEvict("cache")
    public void cacheEvict(String key) {
        System.out.println("----cacheEvict-----");
    }
}
