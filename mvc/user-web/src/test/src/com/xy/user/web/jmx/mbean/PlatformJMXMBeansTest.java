package com.xy.user.web.jmx.mbean;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

public class PlatformJMXMBeansTest {

    public static void main(String[] args) {
        // 客户端去获取 ClassLoadingMXBean 对象（代理对象）
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();

        int count = classLoadingMXBean.getLoadedClassCount();

        System.out.println(count);
    }
}
