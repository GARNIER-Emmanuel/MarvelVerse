package com.manu.marvel.controller;

import com.manu.marvel.model.FavoriteCharacter;
import com.manu.marvel.service.FavoriteCharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites/characters")
@CrossOrigin
public class FavoriteCharacterController {

    private final FavoriteCharacterService service;

    public FavoriteCharacterController(FavoriteCharacterService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FavoriteCharacter> addFavorite(@RequestBody FavoriteCharacter character) {
        return ResponseEntity.ok(service.save(character));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteCharacter>> getAllFavorites() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
