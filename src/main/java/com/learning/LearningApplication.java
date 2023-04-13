package com.learning;

import com.learning.domains.configurations.ConfigurationPropertiesCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class LearningApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(LearningApplication.class, args);
    }


    // test configuration properties
    @Autowired
    ConfigurationPropertiesCustom configurationPropertiesCustom;

    @Override
    public void run(String... args) {
        System.err.println("Author : " + configurationPropertiesCustom.getAuthor());
        System.err.println("Age : " + configurationPropertiesCustom.getAge());
        System.err.println("Career : " + configurationPropertiesCustom.getCareer());
    }
}
