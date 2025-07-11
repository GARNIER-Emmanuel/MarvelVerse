package com.manu.marvel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manu.marvel.entity.FavoriteSerie;

import java.util.List;
import java.util.Optional;

public interface FavoriteSerieRepository extends JpaRepository<FavoriteSerie, Long> {
    Optional<FavoriteSerie> findByTitle(String title);
    List<FavoriteSerie> findByTitleContainingIgnoreCase(String title);
}

