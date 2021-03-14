package com.xy.user.web.jmx.mbean;

import com.sun.jmx.mbeanserver.Introspector;
import com.xy.user.web.domain.User;
import com.xy.user.web.jmx.mbean.UserJmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * jmx测试
 */
public class UserJmxTest {

    public static void main(String[] args) throws Exception {
        //测试当前类是不是MBean
        Introspector.testCompliance(UserJmx.class);
        // 获取平台 MBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 为 UserJmxMBean 定义 ObjectName
        ObjectName objectName = new ObjectName("com.xy.user.web.jmx.mbean:type=UserJmx");
        // 创建 UserMBean 实例
        User user = new User();
        mBeanServer.registerMBean(createUserMBean(user), objectName);
        while (true) {
            Thread.sleep(2000);
            System.out.println(user);
        }
    }

    private static Object createUserMBean(User user) {
        return new UserJmx(user);
    }
}
