package com.learning.apps.controllers;


import com.learning.apps.dtos.MailDTO;
import com.learning.apps.responses.CustomResponse;
import com.learning.domains.configurations.kafka.ProducerConfig;
import com.learning.domains.configurations.kafka.dtos.EventDTO;
import com.learning.domains.configurations.redis.redis_service.RedisMailService;
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
    public CustomResponse<?> testKafka() {
        producerConfig.sendMessage(new EventDTO<>("NORMAL_MESSAGE", "normal message", null));
        return CustomResponse.ok("Send message success!");
    }
}
