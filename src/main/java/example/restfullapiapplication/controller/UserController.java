package example.restfullapiapplication.controller;

import example.restfullapiapplication.domain.User;
import example.restfullapiapplication.service.UserService;
import example.restfullapiapplication.service.error.IdInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.handleCreateUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){
//        return new ResponseEntity<>(userService.handleGetAllUser(), HttpStatus.OK);
        return ResponseEntity.ok().body(userService.handleGetAllUser());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.handleGetUserById(id), HttpStatus.OK);
    }



    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) throws IdInvalidException {
        if(id >= 1500){
            throw new IdInvalidException("ID not found!");
        }
        userService.handleDeleteUserById(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUserById(@RequestBody User user){
        User currentUser = userService.handleGetUserById(user.getId());
        if( currentUser != null){
            currentUser.setEmail(user.getEmail());
            currentUser.setName(user.getName());
            currentUser.setPassword(user.getPassword());
            return new ResponseEntity<>(userService.handleCreateUser(currentUser), HttpStatus.OK);
        }
        return null;

    }
}
