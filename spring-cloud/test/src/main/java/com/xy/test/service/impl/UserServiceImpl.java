package com.xy.test.service.impl;

import com.xy.test.entity.User;
import com.xy.test.mapper.UserMapper;
import com.xy.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * 实现类（包装类）
 *
 * @author xy
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void sayHello(String name) {
        System.out.println("UserServiceImpl...hello..." + name);
    }

    @Override
    public void sayByeBye(String name) {
        System.out.println("UserServiceImpl...bye..." + name);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert() {
        String name = UUID.randomUUID().toString().substring(0, 5);
        userMapper.insert(name);
        System.out.println("执行成功");
    }

    public User getUser() {
        User user = new User();
        user.setId(1);
        user.setName("xy");
        return user;
    }
}