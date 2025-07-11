package com.manu.marvel.controller;

import com.manu.marvel.entity.FavoriteCharacter;
import com.manu.marvel.entity.User;
import com.manu.marvel.repository.FavoriteCharacterRepository;
import com.manu.marvel.repository.UserRepository;
import com.manu.marvel.security.JwtUtil;
import com.manu.marvel.security.UserDetailsImpl;
import com.manu.marvel.service.UserService;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private FavoriteCharacterRepository favoriteRepository;

   @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already taken");
        }
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        org.springframework.security.core.Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    private static class JwtResponse {
        private final String token;
        public JwtResponse(String token) { this.token = token; }
        public String getToken() { return token; }
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
