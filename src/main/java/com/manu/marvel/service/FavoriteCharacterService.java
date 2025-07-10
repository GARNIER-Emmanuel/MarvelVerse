package com.manu.marvel.service;

import com.manu.marvel.model.FavoriteCharacter;
import com.manu.marvel.repository.FavoriteCharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteCharacterService {

    private final FavoriteCharacterRepository repository;

    public FavoriteCharacterService(FavoriteCharacterRepository repository) {
        this.repository = repository;
    }

    public FavoriteCharacter save(FavoriteCharacter character) {
        return repository.save(character);
    }

    public List<FavoriteCharacter> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
