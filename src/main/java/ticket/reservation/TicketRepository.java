package ticket.reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface TicketRepository extends CrudRepository<Ticket, Long>{

}
