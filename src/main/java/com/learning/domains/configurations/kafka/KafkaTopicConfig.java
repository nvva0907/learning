package com.learning.domains.configurations.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.apache.kafka.clients.admin.NewTopic;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.num-partition}")
    private int numPartitions;
    @Value("${spring.kafka.topic.replication-factor}")
    private short replicationFactor;
    @Value("${spring.kafka.producer.template.default-topic}")
    private String commonTopic;

    @Bean
    @Primary
    public NewTopic kafkaCommonTopic() {
        return new NewTopic(commonTopic, numPartitions, replicationFactor);
    }
}
