package com.xy.validator;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class ValidatorApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ValidatorApplication.class)
                .run(args);
    }
}
