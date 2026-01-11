package com.classtest.runner;

import com.classtest.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Task Runner
 * Executes async tasks on application startup
 */
@Component
public class TaskRunner implements CommandLineRunner {

    private final TaskService taskService;

    public TaskRunner(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n========== Starting Async Task Execution ==========\n");
        
        // Call the async method 3 times
        taskService.executeTask(1);
        taskService.executeTask(2);
        taskService.executeTask(3);
        
        System.out.println("\n========== All tasks submitted ==========");
        System.out.println("(Tasks will run asynchronously on thread pool)\n");
        
        // Keep application running to allow async tasks to complete
        Thread.sleep(3000);
        
        System.out.println("\n========== Async Task Execution Complete ==========\n");
    }

}
