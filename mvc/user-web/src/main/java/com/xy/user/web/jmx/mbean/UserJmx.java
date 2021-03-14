package com.xy.user.web.jmx.mbean;

import com.xy.user.web.domain.User;

import javax.management.openmbean.OpenType;

/**
 * 接口的类名称必须以“MBean”为后缀，如MBean 定义为 “XXXMBean”
 * ,那么它的实现类名必须是
 * “XXX”
 */
public class UserJmx implements UserJmxMBean {

    private final User user;

    public UserJmx(User user) {
        this.user = user;
    }

    @Override
    public Long getId() {
        return user.getId();
    }

    @Override
    public void setId(Long id) {
        user.setId(id);
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public void setName(String name) {
        user.setName(name);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public void setPassword(String password) {
        user.setPassword(password);
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public void setEmail(String email) {
        user.setEmail(email);
    }

    @Override
    public String getPhoneNumber() {
        return user.getPhoneNumber();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        user.setPhoneNumber(phoneNumber);
    }

    @Override
    public String toString() {
        return user.toString();
    }

    /**
     * CompositeData {@link OpenType#ALLOWED_CLASSNAMES_LIST#CompositeData}
     *
     * @return user
     */
    @Override
    public User getUser() {
        return this.user;
    }
}
