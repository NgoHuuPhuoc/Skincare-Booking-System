package com.skincare.skincareapp.controller;

import com.skincare.skincareapp.entity.User;
import com.skincare.skincareapp.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/create-or-get")
    public User createOrGetUser(@RequestBody User input) {
        return userRepo.findByPhone(input.getPhone())
                .orElseGet(() -> userRepo.save(User.builder()
                        .name(input.getName())
                        .phone(input.getPhone())
                        .build()));
    }
}
