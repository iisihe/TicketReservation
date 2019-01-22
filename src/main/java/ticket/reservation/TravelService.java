package ticket.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "travelService")
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;


    public List<Travel> getAllTravels(){
        List<Travel> travels = new ArrayList<>();
        travelRepository.findAll()
                .forEach(travels::add);
        return travels;
    }

    public Optional<Travel> getTravel(long id){
        return travelRepository.findById(id);
    }

    public void addTravel(Travel travel){
        travelRepository.save(travel);
    }

    public void updateTravel(long id, Travel travel) {
        travelRepository.save(travel);
    }

    public void deleteTravel(long id) {
        travelRepository.deleteById(id);
    }

    public void reserveSeat(Travel travel, int seat){
        travel.reserveSeat(travel, seat);
        travelRepository.save(travel);
    }

    public void setSeatFree(Travel travel, int seat){
        travel.setSeatFree(travel, seat);
        travelRepository.save(travel);
    }
}
