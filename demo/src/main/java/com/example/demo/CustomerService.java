public class CustomerService {
    @Autowired
    private TicketRepository ticketRepository;

    public boolean purchaseTicket() {
        Optional<Ticket> availableTicket = ticketRepository.findAll().stream()
                .filter(Ticket::isAvailable)
                .findFirst();
        if (availableTicket.isPresent()) {
            Ticket ticket = availableTicket.get();
            ticket.setAvailable(false);
            ticketRepository.save(ticket);
            return true;
        }
        return false;
    }
}
