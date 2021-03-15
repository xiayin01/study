package com.xy.user.web.jmx.mxbeaan;

import com.xy.user.web.domain.Color;
import com.xy.user.web.jolokia.ColorImpl;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class ColorMxBeanTest {

    public static void main(String[] args) throws Exception {
        // 获取平台 MXBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 为 StudentJmxMXBean 定义 ObjectName
        ObjectName objectName = new ObjectName("com.xy.user.web.jolokia:type=Color");
        // 创建 ColorMXBean 实例
        Color color = new Color("红色", 1);
        mBeanServer.registerMBean(createColorMXBean(color), objectName);
        while (true) {
            Thread.sleep(2000);
            System.out.println(color);
        }
    }

    private static Object createColorMXBean(Color color) {
        return new ColorImpl(color);
    }
}
