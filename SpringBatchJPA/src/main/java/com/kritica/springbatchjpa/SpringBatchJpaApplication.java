package com.kritica.springbatchjpa;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchJpaApplication.class, args);
    }

}
