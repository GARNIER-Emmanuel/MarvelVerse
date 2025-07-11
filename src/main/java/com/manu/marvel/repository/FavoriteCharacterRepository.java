package com.manu.marvel.repository;

import com.manu.marvel.model.FavoriteCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteCharacterRepository extends JpaRepository<FavoriteCharacter, Long> {

    Optional<FavoriteCharacter> findByName(String name);

    List<FavoriteCharacter> findByNameContainingIgnoreCase(String name);

}


