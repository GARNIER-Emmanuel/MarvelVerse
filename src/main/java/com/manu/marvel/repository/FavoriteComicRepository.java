package com.manu.marvel.repository;

import com.manu.marvel.model.FavoriteComic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteComicRepository extends JpaRepository<FavoriteComic, Long> {}
