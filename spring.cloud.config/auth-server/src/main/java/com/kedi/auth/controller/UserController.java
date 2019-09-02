package com.kedi.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 凭证控制器
 *
 * @author xy
 */
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 供客户端来获得用户的凭证。
     *
     * @param user 提供信息
     * @return 结果
     */
    @GetMapping
    public Principal user(Principal user) {
        return user;
    }
}
