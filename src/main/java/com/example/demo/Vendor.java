package com.example.demo;

/**
 * The Vendor class represents a producer in the ticketing system.
 * It continuously adds tickets to the ticket pool at a specified rate
 * while ensuring the pool does not exceed its maximum capacity.
 */

public class Vendor implements Runnable{
    private final TicketPool ticketPool;
    private final int releaseRate;
    private final int maxCapacity;

    // Constructor for Vendor.
    public Vendor(TicketPool ticketPool, int releaseRate, int maxCapacity) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
        this.maxCapacity = maxCapacity;
    }

    /**
     * The run method is the entry point for the Vendor thread.
     * It continuously adds tickets to the pool at the specified release rate,
     * while respecting the maximum capacity of the pool.
     */
    @Override
    public void run() { //simulate continuous ticket addition.
        while (true) {
            // Add tickets to the pool. Synchronization is handled within the TicketPool class.
            ticketPool.addTickets(releaseRate, maxCapacity);
            try {
                Thread.sleep(5000); // Simulate vendor restocking
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
