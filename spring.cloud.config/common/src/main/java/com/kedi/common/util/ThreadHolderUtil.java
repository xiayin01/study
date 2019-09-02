package com.kedi.common.util;

import com.kedi.common.user.UserInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 基础相关信息
 *
 * @author xy
 */
public final class ThreadHolderUtil {

    /**
     * 用户标记
     */
    public static final String CONSUMER_ID = "x-consumer-id";
    /**
     * 当前请求线程用户信息
     */
    private static final ThreadLocal<UserInfo> USER_HOLDER = new ThreadLocal<>();
    /**
     * 任意类型数据集合
     */
    private static final ThreadLocal<Map<Object, Object>> VALUE_MAP = ThreadLocal.withInitial(HashMap::new);

    /**
     * 设置当前请求线程用户对象
     *
     * @param userInfo 用户对象
     */
    public static void setUser(UserInfo userInfo) {
        USER_HOLDER.set(userInfo);
    }

    /**
     * 获取当前请求线程用户编号
     *
     * @return 用户编号
     */
    public static long getUserId() {
        UserInfo userInfo = USER_HOLDER.get();
        return Optional.ofNullable(userInfo).map(UserInfo::getUserId).orElse(0L);
    }

    /**
     * 获取当前请求线程用户编号
     *
     * @return 用户姓名
     */
    public static String getUserName() {
        UserInfo userInfo = USER_HOLDER.get();
        return Optional.ofNullable(userInfo).map(UserInfo::getUserName).orElse(null);
    }

    /**
     * 获取当前请求线程用户
     *
     * @return 用户
     */
    public static UserInfo getUser() {
        return USER_HOLDER.get();
    }

    /**
     * 清除用户信息
     */
    public static void clearUser() {
        USER_HOLDER.remove();
    }

    /**
     * 根据key获取值
     *
     * @param key   key
     * @param clazz 值类型
     * @return 值
     */
    public static <T> T getValue(Object key, Class<T> clazz) {
        Object value = Optional.ofNullable(VALUE_MAP.get()).map(map -> map.get(key)).orElse(null);
        try {
            return (T) value;
        } catch (Throwable ex) {
            return null;
        }
    }

    /**
     * 获取map
     */
    public static Map<Object, Object> getValueMap() {
        return VALUE_MAP.get();
    }

    /**
     * 设置key值
     *
     * @param key   key
     * @param value 值
     */
    public static void setValue(Object key, Object value) {
        Optional.ofNullable(VALUE_MAP.get()).ifPresent(valueMap -> valueMap.put(key, value));
    }

    /**
     * 覆盖map
     *
     * @param values map值
     */
    public static void setValueMap(Map<Object, Object> values) {
        VALUE_MAP.set(values);
    }

    /**
     * 清除指定Key
     *
     * @param key 指定key
     */
    public static void clearValue(Object key) {
        Optional.ofNullable(VALUE_MAP.get()).ifPresent(valueMap -> valueMap.remove(key));
    }

    /**
     * 清除整个map
     */
    public static void clearValueMap() {
        VALUE_MAP.remove();
    }
}
