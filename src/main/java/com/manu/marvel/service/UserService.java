package com.manu.marvel.service;

import com.manu.marvel.entity.User;
import com.manu.marvel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(String username, String rawPassword) {
        // Vérifie si l'utilisateur existe déjà
        User existing = userRepository.findByUsername(username);
        if (existing != null) return existing;

        User user = new User();
        user.setUsername(username);
        if (rawPassword != null) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }
        return userRepository.save(user);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        // On encode le mot de passe avant de sauvegarder
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
