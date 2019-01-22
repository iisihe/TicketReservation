package ticket.reservation;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

/*
 * ! HUOM !
 * Salasanan tallennus yms. toteutettu vain testaamista varten, ei ole tietoturvallinen todelliseen käyttöön
 * ! HUOM !
 * */

  @Id
  private String email;
  private String name;
  private String password;
  private long cardNumber;
  private String role;

  public User(){
      this.role = "Käyttäjä";
  }

  public User(String email, String password){
      this.email = email;
      this.password = password;
      this.role = "Käyttäjä";
  }

  public User(String email, String name, String password){
      this.email = email;
      this.name = name;
      this.password = password;
      this.role = "Käyttäjä";
  }

  public User(String email, String name, String password, long cardNumber){
      this.email = email;
      this.name = name;
      this.password = password;
      this.cardNumber = cardNumber;
      this.role = "Käyttäjä";
  }

  public String getEmail() {
      return email;
  }

  public void setEmail(String email) {
      this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public long getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(long cardNumber) {
    this.cardNumber = cardNumber;
  }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
