package com.learning.domains.configurations.redis.redis_service;

import com.learning.apps.dtos.MailDTO;

public interface RedisMailService {

    void publish(MailDTO dto);

    void sendMail(MailDTO dto);
}
