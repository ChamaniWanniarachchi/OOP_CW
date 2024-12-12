package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The TicketController class manages the API endpoints for controlling
 * the ticketing system. It allows configuration, starting/stopping of the system,
 * and querying ticket count in the pool. It demonstrates a producer-consumer
 * system with vendors and customers.
 */

@RestController // Marks this class as a Spring REST controller.
@CrossOrigin // Enables Cross-Origin Resource Sharing (CORS) for frontend integration.
@RequestMapping("/api") // Base URL path for all endpoints in this controller.
public class TicketController {
    // Injects the TicketPool component to manage tickets.
    @Autowired
    private TicketPool ticketPool;
    private Configuration configuration;
    // Manages thread pools for simulating vendors and customers.
    private ExecutorService executorService;

    @PostMapping("/configure")
    public String configureSystem(@RequestBody Configuration config) {
        this.configuration = config;
        return "System configured with " + config.toString();
    }

    @PostMapping("/start")
    public String startSystem() {
        // Ensure the system is configured before starting.
        if (configuration == null) {
            return "Please configure the system first.";
        }

        // Initialize a cached thread pool for dynamic thread management.
        executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) { // Simulate 3 vendors
            executorService.submit(new Vendor(ticketPool, configuration.getTicketReleaseRate(), configuration.getMaxTicketCapacity()));
        }

        for (int i = 0; i < 5; i++) { // Simulate 5 customers
            executorService.submit(new Customer(ticketPool));
        }

        return "System started with 3 vendors and 5 customers.";
    }

    @PostMapping("/stop")
    public String stopSystem() {
        // Check if the thread pool is initialized.
        if (executorService != null) {
            executorService.shutdownNow(); // Shutdown all running threads.
            return "System stopped.";
        }
        return "System is not running.";
    }

    @GetMapping("/ticket-count")
    public int getTicketCount() {
        return ticketPool.getTicketCount(); // Fetch and return the ticket count.
    }
}
