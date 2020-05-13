package com.xy.test;

import com.xy.user.UserApp;
import com.xy.user.enums.UserTypeEnum;
import com.xy.user.service.strategy.UserStrategyService;
import com.xy.user.service.strategy.impl.UserStrategyFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserApp.class})
public class UserStrategyTest {

    @Test
    public void userStrategyTest() {
        UserStrategyService userService = UserStrategyFactory.getUserStrategyService(UserTypeEnum.NORMAL.getText());
        System.out.println(userService);
        userService = UserStrategyFactory.getUserStrategyService(UserTypeEnum.VIP.getText());
        System.out.println(userService);
    }
}
