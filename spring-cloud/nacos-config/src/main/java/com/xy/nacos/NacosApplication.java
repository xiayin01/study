package com.xy.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringCloudApplication
@EnableDiscoveryClient
public class NacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
    }

}
