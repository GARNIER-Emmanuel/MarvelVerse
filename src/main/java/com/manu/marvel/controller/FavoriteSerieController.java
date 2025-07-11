package com.manu.marvel.controller;

import com.manu.marvel.entity.FavoriteSerie;
import com.manu.marvel.service.FavoriteSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites/series")
public class FavoriteSerieController {

    @Autowired
    private FavoriteSerieService service;

    @PostMapping
    public ResponseEntity<FavoriteSerie> add(@RequestBody FavoriteSerie serie) {
        return ResponseEntity.ok(service.save(serie));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteSerie>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<FavoriteSerie>> search(@RequestParam String title) {
        return ResponseEntity.ok(service.search(title));
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<FavoriteSerie>> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.findAllPaged(pageable));
    }
}

