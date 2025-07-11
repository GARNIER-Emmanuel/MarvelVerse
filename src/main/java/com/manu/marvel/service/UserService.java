package com.manu.marvel.service;

import com.manu.marvel.entity.User;
import com.manu.marvel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(String username) {
        User existing = userRepository.findByUsername(username);
        if (existing != null) return existing;

        User user = new User();
        user.setUsername(username);
        return userRepository.save(user);
    }
}
