package ticket.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  /*
  * ! HUOM !
  * Kirjautuminen yms. toteutettu vain testaamista varten, ei ole tietoturvallinen todelliseen käyttöön
  * ! HUOM !
  * */


  @RequestMapping(value="/login", method=RequestMethod.GET)
  public Optional<User> login(@RequestParam("email") String email, @RequestParam("password") String password){

      Optional<User> user = userService.getUser(email);
      if (!user.isPresent()) { // Tarkistetetaan löytyykö käyttäjää
          return null; // Käyttäjää ei löydy
      } else {
          if (password.equals(user.get().getPassword())){ // Tarkistetaan onko salasana oikein
              return user;
          }
          else{
              return null; // Salasana on väärä
          }
      }
  }

  @RequestMapping(value="/logout", method=RequestMethod.GET)
  public String logout(){
      return "Logged out";
  }

  @RequestMapping("/users")
  public List<User> getAllUsers(){
    return userService.getAllUsers();
  }

  @RequestMapping("/users/{email}")
  public Optional<User> getUser(@PathVariable String email){
    return userService.getUser(email);
  }

  @RequestMapping(method=RequestMethod.POST, value="/users")
  public void addUser(@RequestBody User user) {
    userService.addUser(user);
  }

  @RequestMapping(method=RequestMethod.PUT, value="/users/{email}")
  public void updateUser(@RequestBody User user, @PathVariable String email) {
    userService.updateUser(email, user);
  }

  @RequestMapping(method=RequestMethod.DELETE, value="/users/{email}")
  public void deleteUser(@PathVariable String email) {
    userService.deleteUser(email);
  }
}
