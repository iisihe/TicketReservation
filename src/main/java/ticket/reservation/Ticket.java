package ticket.reservation;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket{

    @Id
    @GeneratedValue
    private long id;
    private long travelId;
    private String userName;
    private int seat;


    public Ticket(){
    }

    public Ticket(long id, long travelId, String userName, int seat){
        this.travelId = travelId;
        this.userName = userName;
        this.seat = seat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTravelId() {
        return travelId;
    }

    public void setTravelId(long travelId) {
        this.travelId = travelId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
