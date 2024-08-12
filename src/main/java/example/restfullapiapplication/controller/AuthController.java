package example.restfullapiapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    @PostMapping("/login")
    public ResponseEntity<Void> login(){


        return ResponseEntity.ok().body(null);
    }
}
