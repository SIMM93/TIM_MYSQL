package com.tsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = "com.tsm.entity")
@EnableJpaRepositories(basePackages = "com.tsm.repository")
@EnableScheduling
public class WOW_TSMApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

       /* ConfigurableApplicationContext context = SpringApplication.run(WOW_TSMApplication.class, args);
        context.close();*/
        SpringApplication.run(WOW_TSMApplication.class, args);


    }


}