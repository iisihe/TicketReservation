package ticket.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class TravelController {

    @Autowired
    private TravelService travelService;


    //Hakee kaikki junat
    @RequestMapping(method=RequestMethod.GET, value = "/travels")
    public List<Travel> getAllTravels(){
          return travelService.getAllTravels();
    }

    //Hakee asemat, joilta lähtee junia
    @RequestMapping(method=RequestMethod.GET, value = "/travels/departureStations")
    public List<String> getDepartureStations(){
        ArrayList<String> departureStations = new ArrayList<>();
        for (int i=0; i<travelService.getAllTravels().size(); i++){
            String departureStation = travelService.getAllTravels().get(i).getDepartureStation();
            departureStations.add(departureStation);
        }
        return departureStations;
    }

    //Hakee asemat, joihin saapuu junia
    @RequestMapping(method=RequestMethod.GET, value = "/travels/arrivalStations")
    public List<String> getArrivalStations(){
        ArrayList<String> arrivalStations = new ArrayList<>();
        for (int i=0; i<travelService.getAllTravels().size(); i++){
            String arrivalStation = travelService.getAllTravels().get(i).getArrivalStation();
            arrivalStations.add(arrivalStation);
        }
        return arrivalStations;
    }

    //Hakee junat lähtöaseman perusteella
    @RequestMapping(method=RequestMethod.GET, value = "/travels/search/{departureStation}")
    public List<Travel> getSelectedTravels(@PathVariable String departureStation){
            ArrayList<Travel> selectedTravels = new ArrayList<>();
            for (int i=0; i<travelService.getAllTravels().size(); i++){
                if (travelService.getAllTravels().get(i).getDepartureStation().equals(departureStation)){
                    Travel travel = travelService.getAllTravels().get(i);
                    selectedTravels.add(travel);
                }
            }
            return selectedTravels;
    }

    //Hakee lähtö- ja saapumisasemien perusteella ne junat, joissa on tilaa
    @RequestMapping(method=RequestMethod.GET, value = "/travels/search/{departureStation}/{arrivalStation}/{amountOfPassengers}")
    public List<Travel> getSelectedTravelsFromAndTo(@PathVariable String departureStation, @PathVariable String arrivalStation, @PathVariable int amountOfPassengers){
        ArrayList<Travel> selectedTravels = new ArrayList<>();
        for (int i=0; i<travelService.getAllTravels().size(); i++){
            if (travelService.getAllTravels().get(i).getDepartureStation().equals(departureStation) &&
                    travelService.getAllTravels().get(i).getArrivalStation().equals(arrivalStation) &&
                    travelService.getAllTravels().get(i).getNumberOfSeats() >= amountOfPassengers){ // junassa on vapaita paikkoja
                Travel travel = travelService.getAllTravels().get(i);
                selectedTravels.add(travel);
            }
        }
        return selectedTravels;
    }

    //Hakee matkan id:n perusteella
    @RequestMapping(method=RequestMethod.GET, value = "/travels/{id}")
    public Optional<Travel> getTravel(@PathVariable long id){
        return travelService.getTravel(id);
    }

    //Lisää matkan
    @RequestMapping(method=RequestMethod.POST, value="/travels")
    public void addTravel(@RequestBody Travel travel) {
        travelService.addTravel(travel);
    }

    //Päivittää matkan
    @RequestMapping(method=RequestMethod.PUT, value="/travels/{id}")
    public void updateTravel(@RequestBody Travel travel, @PathVariable long id) {
        travelService.updateTravel(id, travel);
    }

    //Poistaa valitun matkan
    @RequestMapping(method=RequestMethod.GET, value="/travels/{id}/delete")
    public List<Travel> deleteTravel(@PathVariable long id) {
        travelService.deleteTravel(id);
        return travelService.getAllTravels();
    }

    //Varaa valitun paikan
    @RequestMapping(method=RequestMethod.GET, value="/travels/{id}/reserveSeat/{seat}")
    public void reserveSeat(@PathVariable long id, @PathVariable int seat) {
        travelService.reserveSeat(travelService.getTravel(id).get(), seat);
    }

    //Asettaa valitun paikan vapaaksi
    @RequestMapping(method=RequestMethod.GET, value="/travels/{id}/setSeatFree/{seat}")
    public void setSeatFree(@PathVariable long id, @PathVariable int seat) {
        travelService.setSeatFree(travelService.getTravel(id).get(), seat);
    }

    //Hakee vapaiden paikkojen määrän tietystä junasta
    @RequestMapping(method=RequestMethod.GET, value="/travels/{id}/freeSeats")
    public int freeSeats(@PathVariable long id) {
        return travelService.getTravel(id).get().getNumberOfSeats();
    }
}
