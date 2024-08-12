package example.restfullapiapplication.service;

import example.restfullapiapplication.domain.User;
import example.restfullapiapplication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleCreateUser(User user) {
        return userRepository.save(user);
    }

    public List<User> handleGetAllUser() {
        return userRepository.findAll();
    }

    public User handleGetUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return userRepository.findById(id).get();
        }
        return null;
    }

    public void handleDeleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
