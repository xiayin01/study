package com.xy.test.service;

import com.xy.test.entity.User;
import com.xy.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * 用户测试
 *
 * @author xy
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

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
