package com.xy.user.web.service.impl;

import com.xy.user.web.annotation.LocalTransaction;
import com.xy.user.web.domain.User;
import com.xy.user.web.service.UserService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

public class UserServiceImpl implements UserService {

    @Resource(name = "bean/EntityManager")
    private EntityManager entityManager;

    @Override
    @LocalTransaction
    public boolean register(User user) {
        entityManager.persist(user);
        return true;
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByEmailAndPassword(String email, String password) {
        return null;
    }
}
