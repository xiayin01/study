package com.xy.test.proxy.dynamic;

import com.xy.test.service.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理实现类
 *
 * @author xy
 */
public class DynamicProxyUserServiceInvocationHandler implements InvocationHandler {

    private UserService target;

    public DynamicProxyUserServiceInvocationHandler(UserService target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
