@Service
public class VendorService {
    @Autowired
    private TicketRepository ticketRepository;

    public void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            Ticket ticket = new Ticket();
            ticketRepository.save(ticket);
        }
    }
}
