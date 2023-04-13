package com.learning.test.bean_configuration;

import com.learning.domains.repositories.UserRepository;
import com.learning.domains.services.UserService;
import com.learning.domains.services.impl.UserServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class BeanConfiguration {
    @Bean
    UserService userService() {
        return new UserServiceImpl();
    }
}
