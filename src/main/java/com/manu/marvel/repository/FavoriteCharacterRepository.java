package com.manu.marvel.repository;

import com.manu.marvel.model.FavoriteCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteCharacterRepository extends JpaRepository<FavoriteCharacter, Long> {}
