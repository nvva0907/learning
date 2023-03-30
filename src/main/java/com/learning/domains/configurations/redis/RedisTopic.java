package com.learning.domains.configurations.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisTopic {

    @Bean
    public ChannelTopic redisMailTopic() {
        return new ChannelTopic("pub:redis-mail:topic");
    }
}
