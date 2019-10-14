package com.xy.auth.feign;

import com.xy.auth.feign.fallback.UserFeignFallback;
import com.xy.common.user.UserInfo;
import com.xy.common.web.ResponseBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户服务
 *
 * @author xy
 */
@FeignClient(name = "user", fallbackFactory = UserFeignFallback.class)
public interface UserFeign {

    /**
     * 获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @RequestMapping(value = "user", method = RequestMethod.GET)
    ResponseBody<UserInfo> getUser(String userName);
}
