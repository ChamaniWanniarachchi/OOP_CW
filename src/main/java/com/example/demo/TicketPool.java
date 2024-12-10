package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class TicketPool {
    private final Queue<String> tickets = new LinkedList<>();
    private final Object lock = new Object(); // Synchronization lock

    // Add tickets to the pool with capacity check
    public void addTickets(int totalTickets, int maxCapacity) {
        synchronized (lock) {
            while (isFull(maxCapacity)) {
                try {
                    lock.wait(); // Wait until there is space in the pool
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            for (int i = 0; i < totalTickets; i++) {
                if (!isFull(maxCapacity)) {
                    tickets.add("Ticket-" + System.nanoTime());
                }
            }
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
            String ticket = tickets.poll();
            lock.notifyAll(); // Notify producers
            return ticket;
        }
    }

    // Get the current ticket count
    public int getTicketCount() {
        synchronized (lock) {
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
