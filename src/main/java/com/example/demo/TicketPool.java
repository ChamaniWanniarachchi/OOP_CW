package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The TicketPool class manages a shared pool of tickets, implementing a producer-consumer
 * pattern to ensure thread-safe operations. Producers add tickets to the pool, and consumers
 * retrieve tickets, with proper synchronization to handle concurrent access.
 */

@Component // Marks this class as a Spring-managed component for dependency injection.
public class TicketPool {
    // Queue to hold tickets; acts as the ticket pool.
    private final Queue<String> tickets = new LinkedList<>();
    // Lock object for synchronizing producer-consumer operations.
    private final Object lock = new Object();

    // Add tickets to the pool with capacity check
    public void addTickets(int totalTickets, int maxCapacity) {
        synchronized (lock) { // Ensure only one thread accesses this block at a time.
            while (isFull(maxCapacity)) {
                try {
                    lock.wait(); // Wait until there is space in the pool
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            // Add tickets to the pool, so that it does not exceed the maximum capacity.
            for (int i = 0; i < totalTickets; i++) {
                if (!isFull(maxCapacity)) {
                    tickets.add("Ticket-" + System.nanoTime());
                }
            }
            // Log the action for debugging or monitoring purposes.
            System.out.println("Vendor added " + totalTickets + " tickets.");
            lock.notifyAll(); // Notify consumers
        }
    }

    // Remove a ticket from the pool
    public String removeTicket() {
        synchronized (lock) {
            while (tickets.isEmpty()) {
                try {
                    lock.wait(); // Wait until tickets are available
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            // Retrieve ticket from the pool.
            String ticket = tickets.poll();
            lock.notifyAll(); // Notify producers
            return ticket;
        }
    }

    // Get the current ticket count
    public int getTicketCount() {
        synchronized (lock) { // Thread-safe access to the ticket count.
            return tickets.size();
        }
    }

    // Check if the pool is full
    public boolean isFull(int maxCapacity) {
        synchronized (lock) {
            return tickets.size() >= maxCapacity;
        }
    }
}
