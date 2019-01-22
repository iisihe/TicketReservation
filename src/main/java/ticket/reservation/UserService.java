package ticket.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll()
                .forEach(users::add);
        return users;
    }

    public Optional<User> getUser(String email){
        return userRepository.findById(email);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(String email, User user) {
        userRepository.save(user);
    }

    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }
}
