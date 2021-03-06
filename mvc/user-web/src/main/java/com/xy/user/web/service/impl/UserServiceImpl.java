package com.xy.user.web.service.impl;

import com.xy.user.web.domain.User;
import com.xy.user.web.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public boolean register(User user) {
        return false;
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
