package com.classtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main Spring Boot Application with Async Support
 * Demonstrates concurrent task execution using a custom thread pool
 */
@SpringBootApplication
@EnableAsync
public class ClassTest03Application {

    public static void main(String[] args) {
        SpringApplication.run(ClassTest03Application.class, args);
    }

}
