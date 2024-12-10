package com.example.demo;

public class Vendor implements Runnable{
    private final TicketPool ticketPool;
    private final int releaseRate;
    private final int maxCapacity;

    public Vendor(TicketPool ticketPool, int releaseRate, int maxCapacity) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void run() {
        while (true) {
            ticketPool.addTickets(releaseRate, maxCapacity);
            try {
                Thread.sleep(5000); // Simulate vendor restocking
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
