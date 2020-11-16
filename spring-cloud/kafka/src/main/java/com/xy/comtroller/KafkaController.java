package com.xy.comtroller;

import com.xy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class KafkaController {

    private final KafkaTemplate kafkaTemplate;

    @Autowired
    public KafkaController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("send/message")
    public String sendMessage(@RequestParam("message") String message) {
        kafkaTemplate.send("test-1", 0, message, message);
        return message;
    }

    @PostMapping("save/user")
    public String saveUser(@RequestBody User user) {
        kafkaTemplate.send("test-user", 0, user.getName(), user);
        return "success";
    }
}
