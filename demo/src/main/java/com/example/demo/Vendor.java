@Async
@Service
public class Vendor implements Runnable {
    private final TicketPool ticketPool;

    public Vendor(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (true) {
            ticketPool.addTickets(10); // Release rate logic
            Thread.sleep(1000);
        }
    }
}

