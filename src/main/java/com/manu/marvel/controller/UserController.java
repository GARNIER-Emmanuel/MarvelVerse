package com.manu.marvel.controller;

import com.manu.marvel.entity.User;
import com.manu.marvel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestParam String username) {
        return userService.register(username);
    }
}
