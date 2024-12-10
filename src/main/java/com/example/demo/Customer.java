package com.example.demo;

public class Customer implements Runnable{
    private final TicketPool ticketPool;

    public Customer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ticketPool) {
                String ticket = ticketPool.removeTicket();
                if (ticket != null) {
                    System.out.println("Customer purchased: " + ticket);
                } else {
                    System.out.println("No tickets available.");
                }
            }
            try {
                Thread.sleep(500); // Simulate a delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
