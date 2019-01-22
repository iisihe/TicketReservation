package ticket.reservation;

import javax.persistence.*;


@Entity
@Table(name = "travels")
public class Travel {

    @Id
    @GeneratedValue
    private long id;
    private String departureTime;
    private String arrivalTime;
    @Column(name="departure_station")
    private String departureStation;
    private String arrivalStation;
    private int price;
    private boolean[] seatReserved;
    private int numberOfSeats = 60;


    public Travel(){
        // luodaan 60 varaamatonta paikkaa junaan
        this.seatReserved = new boolean[60];
        // asetetaan 55 ensimmäistä paikkaa varatuiksi testaamisen helpottamiseksi
        for (int i=0; i<55; i++){
            seatReserved[i] = true;
            numberOfSeats--;
        }
    }

    public Travel(long id, String departureTime, String arrivalTime, String departureStation, String arrivalStation, int price){
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.price = price;
        this.seatReserved = new boolean[60];
        for (int i=0; i<55; i++){
            seatReserved[i] = true;
            numberOfSeats--;
        }
    }

    public Travel(String departureStation){
        this.departureStation = departureStation;
        this.seatReserved = new boolean[60];
        for (int i=0; i<55; i++){
            seatReserved[i] = true;
            numberOfSeats--;
        }
    }

    public Travel(String departureStation, String arrivalStation){
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.seatReserved = new boolean[60];
        for (int i=0; i<55; i++){
            seatReserved[i] = true;
            numberOfSeats--;
        }
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setDepartureTime(String departureTime){
        this.departureTime = departureTime;
    }

    public String getDepartureTime(){
        return departureTime;
    }

    public void setArrivalTime(String arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalTime(){
        return arrivalTime;
    }

    public void setDepartureStation(String departureStation){
        this.departureStation = departureStation;
    }

    public String getDepartureStation(){
        return departureStation;
    }

    public void setArrivalStation(String arrivalStation){
        this.arrivalStation = arrivalStation;
    }

    public String getArrivalStation(){
        return arrivalStation;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

    public boolean[] getSeatReserved() {
        return seatReserved;
    }

    public void setSeatReserved(boolean[] seatReserved) {
        this.seatReserved = seatReserved;
    }

    public void reserveSeat(Travel travel, int i){
        travel.seatReserved[i] = true;
        travel.numberOfSeats--;
    }

    public void setSeatFree(Travel travel, int i){
        travel.seatReserved[i] = false;
        travel.numberOfSeats++;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int amountOfFreeSeats(){
        int amount = 0;
        for (int i=0; i<60; i++){
            if (seatReserved[i] == false){
                amount++;
            }
        }
        return amount;
    }
}


