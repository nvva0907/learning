package com.learning.domains.configurations.redis.redis_receivers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.apps.dtos.MailDTO;
import com.learning.domains.configurations.redis.redis_service.RedisMailService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import javax.annotation.Resource;

@Slf4j
public class MailReceiver implements MessageListener {
    @Resource
    RedisMailService service;

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("mail {}", message);
        MailDTO value = new ObjectMapper().readValue(message.getBody(), MailDTO.class);
        service.sendMail(value);
    }
}
