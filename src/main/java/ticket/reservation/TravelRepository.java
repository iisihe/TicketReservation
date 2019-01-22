package ticket.reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface TravelRepository extends CrudRepository<Travel, Long> {


}
