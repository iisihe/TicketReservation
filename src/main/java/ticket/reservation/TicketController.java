package ticket.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TravelService travelService;

    //Hakee kaikki liput
    @RequestMapping(method=RequestMethod.GET, value = "/tickets")
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    //Hakee matkan id:n perusteella
    @RequestMapping(method=RequestMethod.GET, value = "/tickets/{id}")
    public Optional<Ticket> getTicket(@PathVariable long id){
        return ticketService.getTicket(id);
    }

    //Lisää lipun
    @RequestMapping(method=RequestMethod.POST, value="/tickets")
    public void addTicket(@RequestBody Ticket ticket) {
        ticketService.addTicket(ticket);
        //Varaa paikan junasta
        travelService.reserveSeat(travelService.getTravel(ticket.getTravelId()).get(), ticket.getSeat());
    }

    //Päivittää lipun
    @RequestMapping(method=RequestMethod.PUT, value="/tickets/{id}")
    public void updateTicket(@RequestBody Ticket ticket, @PathVariable long id) {
        ticketService.updateTicket(id, ticket);
    }

    //Poistaa lipun
    @RequestMapping(method=RequestMethod.GET, value="/tickets/{id}/delete")
    public List<Ticket> deleteTicket(@PathVariable long id) {
        travelService.setSeatFree(travelService.getTravel(ticketService.getTicket(id).get().getTravelId()).get(), ticketService.getTicket(id).get().getSeat());
        ticketService.deleteTicket(id);
        return ticketService.getAllTickets();
    }
}
