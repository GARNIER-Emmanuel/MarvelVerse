package com.manu.marvel.controller;

import com.manu.marvel.entity.FavoriteCharacter;
import com.manu.marvel.service.FavoriteCharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
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

    @GetMapping("/search")
    public ResponseEntity<List<FavoriteCharacter>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(service.searchByName(name));
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<FavoriteCharacter>> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.findAllPaged(pageable));
    }

}
