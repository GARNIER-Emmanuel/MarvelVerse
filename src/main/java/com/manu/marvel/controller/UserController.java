package com.manu.marvel.controller;

import com.manu.marvel.entity.FavoriteCharacter;
import com.manu.marvel.entity.User;
import com.manu.marvel.repository.FavoriteCharacterRepository;
import com.manu.marvel.repository.FavoriteRepository;
import com.manu.marvel.repository.UserRepository;
import com.manu.marvel.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteCharacterRepository favoriteRepository;

    @PostMapping("/register")
    public User register(@RequestParam String username) {
        return userService.register(username);
    }

    // Changer l’URL pour éviter /users/users
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{id}/favorites")
    public List<FavoriteCharacter> getFavoritesByUserId(@PathVariable Long id) {
        return favoriteRepository.findByUserId(id);
    }
}
