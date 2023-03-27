package com.dmdev;

import com.dmdev.dao.UserRepository;
import com.dmdev.util.config.DatabaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AppRunner {

    public static void main(String[] args) {
        var context = SpringApplication.run(AppRunner.class, args);
        System.out.println(context.getBean(DatabaseProperties.class));
        UserRepository userRepository = context.getBean(UserRepository.class);
    }

}