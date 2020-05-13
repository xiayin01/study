package com.xy.user.service.strategy.impl;

import com.xy.user.enums.UserTypeEnum;
import com.xy.user.factory.user.UserStrategyFactory;
import com.xy.user.service.strategy.UserStrategyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VipStrategyServiceImpl implements UserStrategyService, InitializingBean {

    private final UserStrategyFactory userStrategyFactory;

    @Override
    public void strategy() {
        System.out.println("---vip_strategy----");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        userStrategyFactory.addStrategy(UserTypeEnum.VIP.getValue(), this);
    }
}
