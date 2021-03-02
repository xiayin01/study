package com.xy.user.web.service.impl;

import com.xy.user.web.domain.User;
import com.xy.user.web.jndi.JndiConnectionManager;
import com.xy.user.web.mapper.DatabaseUserRepository;
import com.xy.user.web.service.UserService;
import com.xy.user.web.sql.DBConnectionManager;

public class UserServiceImpl implements UserService {

     private final DBConnectionManager dbConnectionManager = new DBConnectionManager();

    //private final JndiConnectionManager dbConnectionManager = new JndiConnectionManager();

    @Override
    public boolean register(User user) {
        return new DatabaseUserRepository(dbConnectionManager).save(user);
    }

    @Override
    public boolean deregister(User user) {
        return new DatabaseUserRepository(dbConnectionManager).save(user);
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
        return new DatabaseUserRepository(dbConnectionManager).getByEmailAndPassword(email, password);
    }
}
