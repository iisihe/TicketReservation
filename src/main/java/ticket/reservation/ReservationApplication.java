package ticket.reservation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;


@SpringBootApplication
public class ReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationApplication.class, args);
    }

    //Luodaan automaattisesti kaksi käyttäjää (admin ja kayttaja) tietokantaan, jos niitä ei vielä ole
    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        Optional<User> admin = userRepository.findById("admin");
        Optional<User> kayttaja = userRepository.findById("kayttaja");
        return (args) -> {
            if (!admin.isPresent()) {
                User user = new User("admin", "Admin", "adminsalasana");
                user.setRole("Ylläpitäjä");
                userRepository.save(user);
                //userRepository.save(new User("admin", "admin", "adminsalasana"));
            }
            if (!kayttaja.isPresent()) {
                userRepository.save(new User("kayttaja", "Kayttaja", "kayttajasalasana"));
            }
        };
    }

    //Luodaan automaattisesti joitakin matkoja tietokantaan, jos niitä ei vielä ole
    @Bean
    public CommandLineRunner demo2(TravelRepository travelRepository) {

        Optional<Travel> travel1 = travelRepository.findById(Long.valueOf(1));
        Optional<Travel> travel2 = travelRepository.findById(Long.valueOf(2));
        Optional<Travel> travel3 = travelRepository.findById(Long.valueOf(3));
        Optional<Travel> travel4 = travelRepository.findById(Long.valueOf(4));
        Optional<Travel> travel5 = travelRepository.findById(Long.valueOf(5));
        Optional<Travel> travel6 = travelRepository.findById(Long.valueOf(6));
        Optional<Travel> travel7 = travelRepository.findById(Long.valueOf(7));
        Optional<Travel> travel8 = travelRepository.findById(Long.valueOf(8));
        return (args) -> {
            if (!travel1.isPresent()) {
                travelRepository.save(new Travel(1, "08.00", "10.00", "Turku", "Helsinki", 12));
            }
            if (!travel2.isPresent()) {
                travelRepository.save(new Travel(2, "12.00", "14.00", "Turku", "Tampere", 10));
            }
            if (!travel3.isPresent()) {
                travelRepository.save(new Travel(3, "16.00", "18.00", "Turku", "Helsinki", 14));
            }
            if (!travel4.isPresent()) {
                travelRepository.save(new Travel(4, "20.00", "22.00", "Turku", "Helsinki", 20));
            }
            if (!travel5.isPresent()) {
                travelRepository.save(new Travel(5, "08.00", "10.00", "Helsinki", "Turku", 12));
            }
            if (!travel6.isPresent()) {
                travelRepository.save(new Travel(6, "12.00", "14.00", "Helsinki", "Turku", 10));
            }
            if (!travel7.isPresent()) {
                travelRepository.save(new Travel(7, "16.00", "18.00", "Helsinki", "Turku", 14));
            }
            if (!travel8.isPresent()) {
                travelRepository.save(new Travel(8, "20.00", "22.00","Tampere", "Turku", 20));
            }
        };
    }
}
