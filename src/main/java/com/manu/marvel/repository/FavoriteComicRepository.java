package com.manu.marvel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manu.marvel.entity.FavoriteComic;

public interface FavoriteComicRepository extends JpaRepository<FavoriteComic, Long> {}
