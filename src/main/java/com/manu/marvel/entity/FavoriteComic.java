package com.manu.marvel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "favorite_comics")
public class FavoriteComic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private String thumbnailUrl;

    // Getters and setters
    // Constructeurs
    public FavoriteComic() {}

    public FavoriteComic(String title, String description, String thumbnailUrl) {
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
    }

    // getters / setters
}
