package com.xy.user.factory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 策略工厂
 */
public interface StrategyFactory {

    /**
     * 获取对应的策略
     *
     * @param name         名字
     * @param requiredType 策略实现类
     * @param <T>          对应的策略
     * @return 对应的策略
     */
    <T> T getStrategy(@NotBlank String name, Class<T> requiredType);

    /**
     * 得到对应的策略
     *
     * @param name 查询条件
     * @return 结果
     */
    Object getStrategy(@NotNull Object name) throws Exception;
}
