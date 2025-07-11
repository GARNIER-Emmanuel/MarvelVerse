package com.manu.marvel.service;

import com.manu.marvel.model.FavoriteSerie;
import com.manu.marvel.repository.FavoriteSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteSerieService {

    @Autowired
    private FavoriteSerieRepository repository;

    public FavoriteSerie save(FavoriteSerie serie) {
        Optional<FavoriteSerie> existing = repository.findByTitle(serie.getTitle());
        return existing.orElseGet(() -> repository.save(serie));
    }

    public List<FavoriteSerie> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<FavoriteSerie> search(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    public Page<FavoriteSerie> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
