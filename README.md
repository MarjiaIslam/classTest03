# Lab Test Three: Async & Thread Pools

**Course:** Advanced Object-Oriented Programming  
**Duration:** 30 minutes  
**Total Marks:** 10

---

## 🎯 Overview

This project demonstrates how to implement background tasks in Spring Boot using a custom thread pool to handle concurrent operations efficiently. It showcases asynchronous programming with configured thread pool management.

---

## 📋 Project Structure

```
classTest03/
├── README.md
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── classtest/
        │           ├── ClassTestApplication.java      (Main application class)
        │           ├── config/
        │           │   └── AsyncConfig.java            (Thread pool configuration)
        │           ├── service/
        │           │   └── TaskService.java            (Async task service)
        │           └── runner/
        │               └── TaskRunner.java             (CommandLineRunner to execute tasks)
        └── resources/
            └── application.properties                   (Spring Boot configuration)
```

---

## ✨ Key Features

### 1. **Async Support Enabled**
   - `@EnableAsync` annotation enables asynchronous method execution
   - Allows methods to run on separate threads without blocking the caller

### 2. **Custom Thread Pool Configuration**
   - **Core Pool Size:** 2 threads (minimum threads always active)
   - **Max Pool Size:** 2 threads (maximum concurrent threads)
   - **Thread Name Prefix:** "LabWorker-" (easy identification in logs)
   - **Queue Capacity:** 100 (tasks queued when all threads are busy)

### 3. **Async Task Service**
   - Simulates background tasks with configurable delays
   - Logs thread information for visibility into concurrent execution

### 4. **Parallel Execution Demonstration**
   - Submits 3 tasks concurrently
   - Shows tasks executing on different threads without waiting

---

## 🏗️ Implementation Details

### **ClassTestApplication.java**
Main Spring Boot application class with `@EnableAsync` annotation:
```java
@SpringBootApplication
@EnableAsync
public class ClassTestApplication { ... }
```

### **AsyncConfig.java**
Custom Executor bean configuration:
```java
@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setThreadNamePrefix("LabWorker-");
        executor.setQueueCapacity(100);
        executor.initialize();
        return executor;
    }
}
```

### **TaskService.java**
Service with async method:
```java
@Service
public class TaskService {
    @Async("taskExecutor")
    public void executeTask(int id) {
        System.out.println("Start Task " + id + " on " + Thread.currentThread().getName());
        Thread.sleep(1000);
        System.out.println("End Task " + id);
    }
}
```

### **TaskRunner.java**
CommandLineRunner to trigger async tasks:
```java
@Component
public class TaskRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        for (int i = 1; i <= 3; i++) {
            taskService.executeTask(i);
        }
    }
}
```

---

## 🚀 Running the Application

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Quick Start (One Command)
```bash
mvn spring-boot:run
```
This command automatically builds and runs the application. You'll see the async tasks executing immediately.

### Manual Build & Run

#### Option 1: Build and Run Separately
```bash
# Build the project
mvn clean install

# Run the JAR file
java -jar target/classtest03-1.0.0.jar
```

#### Option 2: Skip Tests (Faster Build)
```bash
# Build without running tests
mvn clean install -DskipTests

# Run the JAR
java -jar target/classtest03-1.0.0.jar
```

### Expected Output
```
Start Task 1 on LabWorker-1
Start Task 2 on LabWorker-2
Start Task 3 on LabWorker-1
End Task 1
End Task 3
End Task 2
```

**Key Observations:**
- Tasks start **immediately** without waiting for previous ones
- Tasks execute on **LabWorker-1** and **LabWorker-2** (the 2 threads in the pool)
- **Task 3** starts on LabWorker-1 after Task 1 finishes (thread reuse)
- Tasks end in **any order** (Task 1 and 3 may end before Task 2)

---

## 📊 How Async & Thread Pools Work

1. **Submission:** When `executeTask()` is called, it doesn't execute immediately
2. **Queue:** The task goes into the executor's queue
3. **Execution:** Available threads from the pool pick up tasks
4. **Concurrency:** With core pool size = 2, up to 2 tasks run simultaneously
5. **Return:** Method returns immediately (non-blocking)

---

## 🔧 Configuration Properties

Edit `src/main/resources/application.properties`:
```properties
spring.task.execution.pool.core-size=2
spring.task.execution.pool.max-size=2
spring.task.execution.pool.queue-capacity=100
spring.task.execution.thread-name-prefix=LabWorker-
```

---

## 📚 Concepts Covered

- ✅ Asynchronous Programming with `@Async`
- ✅ Thread Pool Management
- ✅ Executor Framework
- ✅ Concurrent Task Execution
- ✅ Spring Boot Configuration

---

## ✅ Lab Requirements Met

| Task | Status |
|------|--------|
| Enable Async Support | ✅ |
| Configure Thread Pool (2/2/LabWorker-) | ✅ |
| Create Async Task Service | ✅ |
| Run & Verify with 3 Tasks | ✅ |
| Console Output showing thread names | ✅ |

---

## 📝 Submission Output

Run the application and capture the console output showing parallel execution with `LabWorker-1` and `LabWorker-2` thread names as evidence of concurrent task execution.

---

## 🔗 References

- [Spring Boot @Async](https://spring.io/guides/gs/async-method/)
- [Spring ThreadPoolTaskExecutor](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/concurrent/ThreadPoolTaskExecutor.html)
- [Java Concurrency in Practice](https://www.oreilly.com/library/view/java-concurrency-in/0596007426/)

---

**Author:** Advanced OOP Student  
**Date:** January 2026
