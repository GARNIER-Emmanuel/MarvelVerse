package com.manu.marvel.controller;

import com.manu.marvel.entity.FavoriteComic;
import com.manu.marvel.service.FavoriteComicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites/comics")
@CrossOrigin // utile plus tard avec Angular
public class FavoriteComicController {

    private final FavoriteComicService service;

    public FavoriteComicController(FavoriteComicService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FavoriteComic> addFavorite(@RequestBody FavoriteComic comic) {
        return ResponseEntity.ok(service.save(comic));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteComic>> getAllFavorites() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
