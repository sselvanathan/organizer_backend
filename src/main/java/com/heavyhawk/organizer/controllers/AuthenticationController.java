package com.heavyhawk.organizer.controllers;

import com.heavyhawk.organizer.models.User;
import com.heavyhawk.organizer.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    final UserRepository userRepository;

    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {

    //TODO check if token exists in firebase

        userRepository.save(user);
    }

}
