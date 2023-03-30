package com.learning.domains.configurations.redis.redis_service.impl;

import com.learning.apps.dtos.MailDTO;
import com.learning.domains.configurations.redis.RedisPublisher;
import com.learning.domains.configurations.redis.RedisTopic;
import com.learning.domains.configurations.redis.redis_service.RedisMailService;
import com.learning.domains.services.ThymeleafService;
import com.learning.domains.utils.JsonUtils;
import com.learning.domains.utils.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Transport;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class RedisMailServiceImpl implements RedisMailService {

    @Resource
    private RedisPublisher redisPublisher;

    @Resource
    private RedisTopic redisTopic;

    @Resource
    MailUtils mailUtils;

    @Resource
    private Environment env;

    @Resource
    private ThymeleafService thymeleafService;

    private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";

    @Override
    public void publish(MailDTO dto) {
        try {
            redisPublisher.publish(redisTopic.redisMailTopic(), JsonUtils.toJson(dto));
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void sendMail(MailDTO dto) {
        try {
            Message message = mailUtils.prepareSendMail(dto.getTos(), dto.getCcs(), null);
            if (!ObjectUtils.isEmpty(message)) {
                Map<String, Object> variables = new HashMap<>();
                variables.put("name", dto.getName());
                message.setSubject("Hello");
                message.setContent(thymeleafService.createContent(env.getProperty("mail.content.demo.template"), variables), CONTENT_TYPE_TEXT_HTML);
                Transport.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Send mail failed");
        }
    }
}
