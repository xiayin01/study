package com.kedi.user.controller;

import com.kedi.common.redis.RedisLock;
import com.kedi.common.user.UserInfo;
import com.kedi.common.web.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 *
 * @author xy
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisLock redisLock;

    /**
     * 获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @GetMapping
    public ResponseBody<UserInfo> getUser(String userName) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName(userName);
        return ResponseBody.success(userInfo);
    }
}
