package com.example.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping(value = "/publish/{message}")
    public void sendMessageToKafkaTopic(@PathVariable String message) {
        this.producer.sendMessage(message);
    }
}
