package com.learning.domains.configurations.redis;

import com.learning.domains.configurations.redis.redis_receivers.MailReceiver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import javax.annotation.Resource;

//@Configuration
//@EnableRedisRepositories
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Resource
    private RedisConfig redisConfig;

    @Resource
    private RedisTopic topic;

//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jedisConnectionFactory = null;
//        try {
//            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
//            jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
//        } catch (RedisConnectionFailureException e) {
//            e.getMessage();
//        }
//        return jedisConnectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate() {
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        template.setValueSerializer(new GenericToStringSerializer<>(String.class));
//        return template;
//    }
//
//    // Receiver setup
//    @Bean
//    public MailReceiver mailReceiver() {
//        return new MailReceiver();
//    }
//
//    @Bean
//    public MessageListenerAdapter mailAdapter() {
//        return new MessageListenerAdapter(mailReceiver());
//    }
//
//    // Listener Container Setup
//    @Bean
//    public RedisMessageListenerContainer messageListenerContainer() {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(redisConfig.jedisConnectionFactory());
//        container.addMessageListener(mailAdapter(), topic.redisMailTopic());
//        return container;
//    }
}
