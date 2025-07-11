package com.manu.marvel.controller;

import com.manu.marvel.entity.Serie;
import com.manu.marvel.service.MarvelSerieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
@CrossOrigin(origins = "*")
public class MarvelSerieController {

    private final MarvelSerieService serieService;

    public MarvelSerieController(MarvelSerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping
    public List<Serie> getSeries(@RequestParam(required = false) String title) {
        return serieService.getSeries(title);
    }
}
