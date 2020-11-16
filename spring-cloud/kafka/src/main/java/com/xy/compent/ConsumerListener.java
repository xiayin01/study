package com.xy.compent;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {

    @KafkaListener(topics = "test-1")
    public void consumer(String message) {
        System.out.println(message);
    }

    @KafkaListener(topics = "test-user")
    public void consumerObject(Object object) {
        System.out.println(object);
    }
}
