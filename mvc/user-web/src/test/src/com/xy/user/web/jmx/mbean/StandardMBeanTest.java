package com.xy.user.web.jmx.mbean;

import com.xy.user.web.domain.User;
import com.xy.user.web.jmx.mbean.UserJmx;
import com.xy.user.web.jmx.mbean.UserJmxMBean;

import javax.management.MBeanInfo;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

/**
 * {@link StandardMBean}
 * 静态的 MBean 接口转化成 DynamicMBean 测试
 */
public class StandardMBeanTest {

    public static void main(String[] args) throws NotCompliantMBeanException {
        // 将静态的 MBean 接口转化成 DynamicMBean
        StandardMBean standardMBean = new StandardMBean(new UserJmx(new User()), UserJmxMBean.class);

        MBeanInfo mBeanInfo = standardMBean.getMBeanInfo();

        System.out.println(mBeanInfo);
    }
}
