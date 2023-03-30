package com.learning.domains.configurations.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
public class RedisPublisher {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public void publish(ChannelTopic topic, Object channelMessage) {
        redisTemplate.convertAndSend(topic.getTopic(), channelMessage);
    }
}
