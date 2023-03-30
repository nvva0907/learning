package com.learning.domains.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.mail.*;
import java.util.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

@Service
@Slf4j
public class MailUtils {
    @Resource
    private Environment env;

    public Message prepareSendMail(List<String> tos, List<String> ccs, String from) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", env.getProperty("config.mail.host"));
        props.put("mail.smtp.starttls.enable", env.getProperty("config.mail.smtp.starttls.enable"));
        props.put("mail.smtp.starttls.required", env.getProperty("config.mail.smtp.starttls.required"));
        props.put("mail.smtp.auth", env.getProperty("config.mail.smtp.auth"));
        props.put("mail.smtp.port", env.getProperty("config.mail.port"));
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(env.getProperty("config.mail.username"), env.getProperty("config.mail.password"));
            }
        });
        Message message = new MimeMessage(session);
        // SET CC
        List<InternetAddress> internetAddressCC = new ArrayList<>();
        if (!ObjectUtils.isEmpty(ccs)) {
            for (String receiver : ccs) {
                if (!ObjectUtils.isEmpty(receiver)) {
                    InternetAddress address = new InternetAddress(receiver);
                    internetAddressCC.add(address);
                }
            }
            InternetAddress[] cc = new InternetAddress[internetAddressCC.size()];
            cc = internetAddressCC.toArray(cc);
            message.setRecipients(Message.RecipientType.CC, cc);
        }
        // SET TO
        List<InternetAddress> internetAddressTO = new ArrayList<>();
        if (!ObjectUtils.isEmpty(tos)) {
            for (String receiver : tos) {
                if (!ObjectUtils.isEmpty(receiver)) {
                    InternetAddress address = new InternetAddress(receiver);
                    internetAddressTO.add(address);
                }
            }
            InternetAddress[] to = new InternetAddress[internetAddressTO.size()];
            to = internetAddressTO.toArray(to);
            message.setRecipients(Message.RecipientType.TO, to);
        }

        if (!ObjectUtils.isEmpty(message.getRecipients(Message.RecipientType.TO))) {
            log.info("all receiver : " + Arrays.toString(message.getAllRecipients()));
        } else {
            return null;
        }
        return message;
    }

}
