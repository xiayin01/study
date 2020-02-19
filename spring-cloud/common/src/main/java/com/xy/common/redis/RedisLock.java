package com.xy.common.redis;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Objects;

/**
 * redis分布式锁（单机）
 *
 * @author xy
 */
public class RedisLock {

    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 重试时间
     */
    private static final long DEFAULT_RETRY_MILLIS = 30L;
    /**
     * 锁的后缀
     */
    private static final String LOCK_SUFFIX = "_redis_lock";
    /**
     * 锁超时时间，防止线程在入锁以后，防止阻塞后面的线程无法获取锁
     */
    private long expireMsecs = 30 * 1000L;
    /**
     * 是否锁定标志
     */
    private volatile boolean locked = false;

    public RedisLock() {
    }

    /**
     * 构造器
     *
     * @param redisTemplate redisTemplate
     * @param expireMsecs   过期时间
     */
    public RedisLock(RedisTemplate<String, Object> redisTemplate, long expireMsecs) {
        this.redisTemplate = redisTemplate;
        this.expireMsecs = expireMsecs;
    }

    /**
     * 获取锁判断
     *
     * @param lockKey 锁名
     * @return true，false
     */
    public boolean tryLock(String lockKey) {
        /**
         * 线程获取锁的等待时间
         */
        long timeOutMsecs = 20 * 1000L;
        return getLock(lockKey + LOCK_SUFFIX, timeOutMsecs, expireMsecs);
    }

    /**
     * 获取锁判断
     *
     * @param lockKey      锁名
     * @param timeOutMsecs 过期时间
     * @return true，false
     */
    public boolean tryLock(String lockKey, long timeOutMsecs) {
        return getLock(lockKey + LOCK_SUFFIX, timeOutMsecs, expireMsecs);
    }

    /**
     * 通过key得到对应的结果
     *
     * @param key key
     * @return 对应的结果
     */
    private String get(final String key) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return Objects.nonNull(valueOperations) ? valueOperations.get(key).toString() : null;
    }

    /**
     * 判断key是否存在
     *
     * @param key   key
     * @param value value
     * @return true, false
     */
    private boolean setNx(final String key, final String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 封装和jedis方法
     *
     * @param key   key
     * @param value value
     * @return 结果
     */
    private String getSet(final String key, final String value) {
        String obj = redisTemplate.opsForValue().getAndSet(key, value).toString();
        return Objects.nonNull(obj) ? obj : null;
    }

    /**
     * 获取锁
     *
     * @param timeoutMsecs 获取的超时时间
     * @param expireMsecs  锁的有效期
     * @return 获取锁成功返回true，超时返回false
     */
    private synchronized boolean getLock(final String lockKey, long timeoutMsecs, long expireMsecs) {
        if (StringUtils.isBlank(lockKey)) {
            logger.error("lockKey不能为空");
            return false;
        }
        while (timeoutMsecs >= 0) {
            long expires = System.currentTimeMillis() + expireMsecs + 1;
            // 锁到期时间
            String expiresStr = String.valueOf(expires);
            if (this.setNx(lockKey, expiresStr)) {
                locked = true;
                return true;
            }
            // redis里key的时间
            String currentValue = this.get(lockKey);
            // 判断锁是否已经过期，过期则重新设置并获取
            if (Objects.nonNull(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
                // 设置锁并返回旧值
                String oldValue = this.getSet(lockKey, expiresStr);
                // 比较锁的时间，如果不一致则可能是其他锁已经修改了值并获取
                if (oldValue != null && oldValue.equals(currentValue)) {
                    locked = true;
                    return true;
                }
            }
            timeoutMsecs -= DEFAULT_RETRY_MILLIS;
            // 延时
            try {
                Thread.sleep(DEFAULT_RETRY_MILLIS);
            } catch (Exception e) {
                logger.error(e.getMessage());
                return false;
            }
        }
        return false;
    }

    /**
     * 释放获取到的锁(单机redis使用synchronized)
     */
    public synchronized void unlock(final String lockKey) {
        if (locked) {
            redisTemplate.delete(lockKey);
            locked = false;
        }
    }
}
