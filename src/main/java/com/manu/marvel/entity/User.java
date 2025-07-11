package com.manu.marvel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id") // ou le nom de ta colonne PK réelle
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
}
