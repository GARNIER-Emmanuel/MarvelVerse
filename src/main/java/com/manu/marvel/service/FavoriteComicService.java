package com.manu.marvel.service;

import com.manu.marvel.model.FavoriteComic;
import com.manu.marvel.repository.FavoriteComicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteComicService {

    private final FavoriteComicRepository repository;

    public FavoriteComicService(FavoriteComicRepository repository) {
        this.repository = repository;
    }

    public FavoriteComic save(FavoriteComic comic) {
        return repository.save(comic);
    }

    public List<FavoriteComic> findAll() {
        return repository.findAll();
    }
}
