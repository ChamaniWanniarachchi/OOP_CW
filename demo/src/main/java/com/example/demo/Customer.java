@Async
@Service
public class Customer implements Runnable {
    private final TicketPool ticketPool;

    public Customer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (true) {
            ticketPool.removeTickets(5); // Release rate logic
            Thread.sleep(1000);
        }
    }
}

