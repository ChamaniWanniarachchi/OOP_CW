import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TicketPool {
    private static final Logger logger = LoggerFactory.getLogger(TicketPool.class);
    private final List<String> tickets = Collections.synchronizedList(new LinkedList<>());

    // Add tickets to the pool
    public void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            tickets.add("Ticket-" + (tickets.size() + 1));
        }
        logger.info("{} tickets added to the pool.", count);
    }

    // Remove a ticket from the pool
    public synchronized String removeTicket() {
        if (tickets.isEmpty()) {
            logger.warn("No tickets available to remove!");
            return null;
        }
        String ticket = tickets.remove(0);
        logger.info("Ticket removed: {}", ticket);
        return ticket;
    }

    // Check the size of the pool
    public int getSize() {
        int size = tickets.size();
        logger.debug("Ticket pool size: {}", size);
        return size;
    }
}
