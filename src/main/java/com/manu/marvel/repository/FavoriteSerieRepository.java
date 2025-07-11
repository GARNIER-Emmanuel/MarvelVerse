package com.manu.marvel.repository;

import com.manu.marvel.model.FavoriteSerie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteSerieRepository extends JpaRepository<FavoriteSerie, Long> {
    Optional<FavoriteSerie> findByTitle(String title);
    List<FavoriteSerie> findByTitleContainingIgnoreCase(String title);
}

