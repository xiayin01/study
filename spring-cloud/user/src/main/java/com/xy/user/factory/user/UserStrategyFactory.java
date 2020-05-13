package com.xy.user.factory.user;

import com.xy.common.exception.NoDefinitionException;
import com.xy.user.enums.UserTypeEnum;
import com.xy.user.factory.StrategyFactory;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户策略工厂实现类
 */
@Configuration
public class UserStrategyFactory implements StrategyFactory {

    private final Map<String, Object> strategyMap;

    public UserStrategyFactory() {
        this.strategyMap = new ConcurrentHashMap<>();
    }

    public UserStrategyFactory(Map<String, Object> strategy) {
        this.strategyMap = strategy;
    }

    public void addStrategy(String name, Object strategy) {
        this.strategyMap.put(name, strategy);
    }

    @Override
    public <T> T getStrategy(@NotBlank String name, Class<T> requiredType) {
        return null;
    }

    @Override
    public Object getStrategy(Object name) throws ClassCastException, NoDefinitionException {
        String userTypeValue = (String) name;
        UserTypeEnum userTypeEnum = UserTypeEnum.getUserTypeEnumByValue(userTypeValue);
        if (userTypeEnum == null) {
            throw new NoDefinitionException((String) name, "无对应的用户类型");
        }
        return strategyMap.get(userTypeEnum.getValue());
    }
}
