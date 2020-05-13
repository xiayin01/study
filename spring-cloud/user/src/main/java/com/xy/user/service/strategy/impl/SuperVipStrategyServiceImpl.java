package com.xy.user.service.strategy.impl;

import com.xy.user.enums.UserTypeEnum;
import com.xy.user.service.strategy.UserStrategyService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class SuperVipStrategyServiceImpl implements UserStrategyService, InitializingBean {

    @Override
    public void strategy() {
        System.out.println("----supperVip_strategy----");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        UserStrategyFactory.registerUserStrategy(UserTypeEnum.VIP.getText(), this);
    }
}
