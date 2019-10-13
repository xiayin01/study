package com.xy.test.proxy.statics;

import com.xy.test.service.UserService;

/**
 * 静态代理类
 *
 * @author xy
 */
public class StaticProxyUserServiceImpl implements UserService {

    private UserService target;

    public StaticProxyUserServiceImpl(UserService target) {
        this.target = target;
    }

    @Override
    public void insert() {
        System.out.println("StaticProxyUserServiceImpl...insert()");
    }

    @Override
    public void sayHello(String name) {
        System.out.println("StaticProxyUserServiceImpl...hello..." + name);
        target.sayHello(name);
    }

    @Override
    public void sayByeBye(String name) {
        System.out.println("StaticProxyUserServiceImpl...bye..." + name);
        target.sayByeBye(name);
    }
}