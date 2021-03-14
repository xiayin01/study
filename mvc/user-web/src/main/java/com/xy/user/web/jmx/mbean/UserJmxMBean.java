package com.xy.user.web.jmx.mbean;

import com.xy.user.web.domain.User;

import javax.management.openmbean.OpenType;

/**
 * {@link User} MBean 接口描述
 */
public interface UserJmxMBean {

    // MBeanAttributeInfo 属性列表
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getPassword();

    void setPassword(String password);

    String getEmail();

    void setEmail(String email);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    // MBeanOperationInfo
    String toString();

    /**
     * CompositeData（合成类型） {@link OpenType#ALLOWED_CLASSNAMES_LIST#CompositeData}
     *
     * @return user
     */
    User getUser();
}
