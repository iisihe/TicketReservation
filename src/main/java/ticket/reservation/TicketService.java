package ticket.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "ticketService")
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets(){
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll()
                .forEach(tickets::add);
        return tickets;
    }

    public Optional<Ticket> getTicket(long id){
        return ticketRepository.findById(id);
    }

    public void addTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void updateTicket(long id, Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void deleteTicket(long id) {
        ticketRepository.deleteById(id);
    }
}
