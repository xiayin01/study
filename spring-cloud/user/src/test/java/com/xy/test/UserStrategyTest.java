package com.xy.test;

import com.xy.common.exception.NoDefinitionException;
import com.xy.user.UserApp;
import com.xy.user.enums.UserTypeEnum;
import com.xy.user.factory.user.UserStrategyFactory;
import com.xy.user.service.strategy.UserStrategyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserApp.class})
public class UserStrategyTest {

    @Autowired
    private UserStrategyFactory userServiceFactory;

    @Test
    public void userStrategyTest() throws NoDefinitionException {
        UserStrategyService userService = (UserStrategyService) userServiceFactory.getStrategy(UserTypeEnum.NORMAL.getValue());
        System.out.println(userService);
        userService = (UserStrategyService) userServiceFactory.getStrategy(UserTypeEnum.VIP.getValue());
        System.out.println(userService);
    }
}
