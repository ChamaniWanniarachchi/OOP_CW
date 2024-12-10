package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class TicketPool {
    private final Queue<String> tickets = new LinkedList<>();

    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            tickets.add("Ticket-" + System.nanoTime());
        }
    }

    public synchronized String removeTicket() {
        return tickets.poll();
    }

    public synchronized int getTicketCount() {
        return tickets.size();
    }

    public synchronized boolean isFull(int maxCapacity) {
        return tickets.size() >= maxCapacity;
    }
}
