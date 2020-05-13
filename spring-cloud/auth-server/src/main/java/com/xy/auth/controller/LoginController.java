package com.xy.auth.controller;

import com.xy.common.web.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注销控制器
 *
 * @author xy
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    /**
     * 注销
     *
     * @param access_token token
     * @return 结果
     */
    @DeleteMapping(value = "/exit")
    public ResponseBody<String> revokeToken(String access_token) {
        if (consumerTokenServices.revokeToken(access_token)) {
            return ResponseBody.success("注销成功");
        }
        return ResponseBody.fail("注销失败");
    }
}
