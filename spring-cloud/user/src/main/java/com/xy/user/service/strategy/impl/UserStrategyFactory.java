package com.xy.user.service.strategy.impl;

import com.xy.user.service.strategy.UserStrategyService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserStrategyFactory {

    private static Map<String, UserStrategyService> userStrategyMap = new ConcurrentHashMap<>();

    /**
     * 根据用户类型，获取对应的策略
     *
     * @param userType 用户类型
     * @return 策略
     */
    public static UserStrategyService getUserStrategyService(String userType) {
        return userStrategyMap.get(userType);
    }

    /**
     * 注入不同的策略
     *
     * @param userType            用户类型
     * @param userStrategyService 用户策略
     */
    public static void registerUserStrategy(String userType, UserStrategyService userStrategyService) {
        Assert.notNull(userType, "userType can't null");
        userStrategyMap.put(userType, userStrategyService);
    }
}
