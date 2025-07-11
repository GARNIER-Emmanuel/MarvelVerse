package com.manu.marvel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manu.marvel.entity.FavoriteCharacter;

import java.util.List;
import java.util.Optional;

public interface FavoriteCharacterRepository extends JpaRepository<FavoriteCharacter, Long> {

    Optional<FavoriteCharacter> findByName(String name);

    List<FavoriteCharacter> findByNameContainingIgnoreCase(String name);
    List<FavoriteCharacter> findByUserId(Long userId);

}


