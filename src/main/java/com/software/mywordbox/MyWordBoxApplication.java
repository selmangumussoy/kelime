package com.software.mywordbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyWordBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWordBoxApplication.class, args);
    }

}
