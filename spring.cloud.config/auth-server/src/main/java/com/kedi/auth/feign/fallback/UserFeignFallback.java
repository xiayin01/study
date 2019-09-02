package com.kedi.auth.feign.fallback;

import com.kedi.auth.feign.UserFeign;
import com.kedi.common.web.Error;
import com.kedi.common.web.ResponseBody;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务回滚类
 *
 * @author xy
 */
@Component
public class UserFeignFallback implements FallbackFactory<UserFeign> {

    @Override
    public UserFeign create(Throwable throwable) {
        return userName -> ResponseBody.isFallback(new Error("调用用户服务异常,回滚", throwable));
    }
}
