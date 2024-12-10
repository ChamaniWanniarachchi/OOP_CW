package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TicketController {
    @Autowired
    private TicketPool ticketPool;

    private Configuration configuration;
    private ExecutorService executorService;

    @PostMapping("/configure")
    public String configureSystem(@RequestBody Configuration config) {
        this.configuration = config;
        return "System configured with " + config.toString();
    }

    @PostMapping("/start")
    public String startSystem() {
        if (configuration == null) {
            return "Please configure the system first.";
        }

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
        if (executorService != null) {
            executorService.shutdownNow();
            return "System stopped.";
        }
        return "System is not running.";
    }

    @GetMapping("/ticket-count")
    public int getTicketCount() {
        return ticketPool.getTicketCount();
    }
}
