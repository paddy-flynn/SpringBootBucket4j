package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RateLimitTestApplication {

    private static Logger LOG = LoggerFactory.getLogger(RateLimitTestApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(RateLimitTestApplication.class, args);
    }

}
