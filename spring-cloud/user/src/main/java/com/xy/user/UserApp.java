package com.xy.user;

import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.SpringVersion;

/**
 * 用户启动类
 *
 * @author xy
 */
@SpringCloudApplication
@EnableFeignClients
@EnableAspectJAutoProxy
@EnableCaching
public class UserApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UserApp.class)
                .main(SpringVersion.class) // 这个是为了可以加载 Spring 版本
                .bannerMode(Banner.Mode.CONSOLE)// 控制台打印
                .run(args);
    }
}
