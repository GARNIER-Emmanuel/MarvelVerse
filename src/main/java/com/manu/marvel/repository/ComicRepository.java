package com.manu.marvel.repository;

import com.manu.marvel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends JpaRepository<User, Long> {
}
