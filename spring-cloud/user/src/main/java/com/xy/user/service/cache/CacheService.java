package com.xy.user.service.cache;

/**
 * 缓存业务层
 */
public interface CacheService {

    /**
     * 获取缓存数据
     *
     * @param key key
     * @return 结果
     */
    String getCacheData(String key);

    /**
     * 新增缓存
     *
     * @param value 数据
     */
    void cachePut(String value);

    /**
     * 删除缓存
     *
     * @param key key
     */
    void cacheEvict(String key);
}
