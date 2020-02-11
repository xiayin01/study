package com.xy.user.controller;

import com.xy.common.redis.RedisLock;
import com.xy.common.user.UserInfo;
import com.xy.common.web.ResponseBody;
import com.xy.user.dto.UserDto;
import com.xy.user.service.UserService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisLock redisLock;
    private final UserService userService;

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

    @GetMapping("user")
    public UserDto getUser(Long id) {
        return userService.getUser(id);
    }
}