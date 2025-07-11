package com.manu.marvel.service;

import com.manu.marvel.entity.FavoriteCharacter;
import com.manu.marvel.entity.User;
import com.manu.marvel.repository.FavoriteCharacterRepository;
import com.manu.marvel.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteCharacterService {

    private final FavoriteCharacterRepository repository;
    private final UserRepository userRepository;
    
    public FavoriteCharacterService(FavoriteCharacterRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<FavoriteCharacter> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public FavoriteCharacter save(FavoriteCharacter character) {
        Optional<FavoriteCharacter> existing = repository.findByName(character.getName());
        if (existing.isPresent()) {
            return existing.get(); // on ne recrée pas le favori, on retourne l'existant
        }
        return repository.save(character);
    }

    public List<FavoriteCharacter> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Page<FavoriteCharacter> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public FavoriteCharacter addFavoriteCharacter(FavoriteCharacter character, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        character.setUser(user);
        return repository.save(character);
    }
    public List<FavoriteCharacter> getFavoritesByUser(Long userId) {
        return repository.findByUserId(userId);
    }
    
}
