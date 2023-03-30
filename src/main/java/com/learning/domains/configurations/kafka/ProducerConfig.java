package com.learning.domains.configurations.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.learning.domains.configurations.kafka.dtos.EventDTO;
import com.learning.domains.entities.EntityTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerConfig {
    private final NewTopic kafkaTopic;
    private final KafkaTemplate<String, String> kafkaTemplate;


    public ProducerConfig(NewTopic kafkaTopic, KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTopic = kafkaTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(EventDTO<EntityTest> eventDTO) {
        try {
            log.info("ORDER EVENT TYPE      => {}", eventDTO.getType());
            log.info("ORDER EVENT MESSAGE   => {}", eventDTO);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(eventDTO);
            Message<String> message = MessageBuilder.withPayload(json).setHeader(KafkaHeaders.TOPIC, kafkaTopic.name()).build();
            kafkaTemplate.send(message);
        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
        }
    }
}
