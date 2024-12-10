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
            synchronized (ticketPool) {
                if (!ticketPool.isFull(maxCapacity)) {
                    ticketPool.addTickets(releaseRate);
                    System.out.println("Vendor added " + releaseRate + " tickets.");
                }
            }
            try {
                Thread.sleep(1000); // Simulate a delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
