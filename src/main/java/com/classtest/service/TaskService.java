package com.classtest.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Task Service
 * Provides async task execution methods
 */
@Service
public class TaskService {

    /**
     * Async Task Method
     * Accepts a task ID and prints start/end messages with thread information
     * Simulates work with a 1-second sleep
     */
    @Async("taskExecutor")
    public void executeTask(int taskId) {
        try {
            // Print task start with thread name
            System.out.println("Start Task " + taskId + " on " + Thread.currentThread().getName());
            
            // Simulate work
            Thread.sleep(1000);
            
            // Print task end
            System.out.println("End Task " + taskId);
        } catch (InterruptedException e) {
            System.err.println("Task " + taskId + " was interrupted");
            Thread.currentThread().interrupt();
        }
    }

}
