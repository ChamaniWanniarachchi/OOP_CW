package com.example.demo;

/**
 * The Customer class represents a consumer in the ticketing system.
 * It continuously retrieves tickets from the ticket pool, simulating
 * customers purchasing tickets.
 */

public class Customer implements Runnable{
    private final TicketPool ticketPool;

    public Customer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    /**
     * The run method is the entry point for the Customer thread.
     * It continuously retrieves tickets from the pool and simulates a
     * customer purchasing tickets at regular intervals.
     */

    @Override
    public void run() {
        while (true) {
            String ticket = ticketPool.removeTicket();
            if (ticket != null) {
                System.out.println(Thread.currentThread().getName() + " purchased: " + ticket);
            }
            try {
                Thread.sleep(1000); // Simulate customer buying interval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
