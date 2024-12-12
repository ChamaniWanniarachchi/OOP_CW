package com.example.demo;

// Importing annotations from the Lombok library
import lombok.AllArgsConstructor; //generates a constructor with all class fields as arguments.
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Configuration class is used to define and manage
 * the settings for the ticketing system, such as the total
 * number of tickets, rates of release and retrieval, and
 * maximum ticket capacity.
 */

@Data //generates boilerplate code such as getters, setters, toString, etc.
@NoArgsConstructor //generates a no-argument constructor.
@AllArgsConstructor //generates a constructor with all class fields as arguments.
public class Configuration {
    private int totalTickets; // The total number of tickets available in the system.
    private int ticketReleaseRate; // The rate at which tickets are released into the system.
    private int customerRetrievalRate; // The rate at which customers retrieve tickets from the system.
    private int maxTicketCapacity; // The maximum number of tickets that can be held at a time in the system.

    //Getters and setters
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }
}
