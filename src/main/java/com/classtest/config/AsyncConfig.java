package com.classtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Async Configuration
 * Defines a custom thread pool executor for handling async tasks
 */
@Configuration
public class AsyncConfig {

    /**
     * Custom Executor Bean
     * Core Pool Size: 2
     * Max Pool Size: 2
     * Thread Name Prefix: "LabWorker-"
     */
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setThreadNamePrefix("LabWorker-");
        executor.initialize();
        return executor;
    }

}
