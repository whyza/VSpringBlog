package com.whyzaa.vspringblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class vSpringBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(vSpringBlogApplication.class, args);
    }
}
