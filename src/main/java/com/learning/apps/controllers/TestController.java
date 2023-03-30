package com.learning.apps.controllers;


import com.learning.apps.dtos.MailDTO;
import com.learning.domains.configurations.kafka.ProducerConfig;
import com.learning.domains.configurations.kafka.dtos.EventDTO;
import com.learning.domains.configurations.redis.redis_service.RedisMailService;
import com.learning.domains.entities.EntityTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Resource
    RedisMailService redisMailService;
    @Resource
    ProducerConfig producerConfig;

    @GetMapping(value = "/redis")
    public Object testRedis() {
        redisMailService.publish(new MailDTO(Collections.singletonList("nvvanh1@gmail.com"), null, "Viet Anh"));
        return "Testing Redis ...";
    }

    @GetMapping(value = "/kafka")
    public Object testKafka() {
        producerConfig.sendMessage(new EventDTO<>("NORMAL_MESSAGE", "normal message", new EntityTest(1L, "viet anh", 24)));
        return "Testing Kafka ...";
    }
}
