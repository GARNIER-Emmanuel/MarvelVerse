package com.manu.marvel.controller;

import com.manu.marvel.dto.CharacterDto;
import com.manu.marvel.dto.ComicDto;
import com.manu.marvel.service.MarvelApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marvel")
public class MarvelController {

    private final MarvelApiService marvelApiService;

    public MarvelController(MarvelApiService marvelApiService) {
        this.marvelApiService = marvelApiService;
    }

    @GetMapping("/characters")
    public ResponseEntity<?> getCharacters(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset
    ) {
        if (name != null && !name.isEmpty()) {
            return ResponseEntity.ok(marvelApiService.searchCharactersByName(name));
        } else {
            return ResponseEntity.ok(marvelApiService.getCharacterDtos());
        }
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<?> getCharacterById(@PathVariable int id) {
        CharacterDto character = marvelApiService.getCharacterById(id);
        if (character != null) {
            return ResponseEntity.ok(character);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Character not found");
        }
    }

    @GetMapping("/characters/{id}/comics")
    public ResponseEntity<List<ComicDto>> getComicsByCharacterId(@PathVariable int id) {
        return ResponseEntity.ok(marvelApiService.getComicsByCharacterId(id));
    }

    @GetMapping("/comics/{id}")
    public ResponseEntity<ComicDto> getComicById(@PathVariable int id) {
        ComicDto comic = marvelApiService.getComicById(id);
        if (comic != null) {
            return ResponseEntity.ok(comic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/comics/search")
    public ResponseEntity<List<ComicDto>> searchComics(@RequestParam String title) {
        List<ComicDto> results = marvelApiService.searchComicsByTitle(title);
        return ResponseEntity.ok(results);
    }


}
