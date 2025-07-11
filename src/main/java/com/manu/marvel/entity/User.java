package com.manu.marvel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ou AUTO
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }
}
