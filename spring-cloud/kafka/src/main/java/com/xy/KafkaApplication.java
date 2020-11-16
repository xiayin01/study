package com.xy;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class KafkaApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(KafkaApplication.class).run(args);
    }
}
