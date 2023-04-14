package com.learning.domains.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "spring")
public class ConfigurationPropertiesCustom {

}
