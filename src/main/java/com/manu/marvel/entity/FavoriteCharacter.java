package com.manu.marvel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "favorite_characters")
public class FavoriteCharacter {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2000)
    private String description;

    private String thumbnailUrl;

    public FavoriteCharacter() {}

    public FavoriteCharacter(String name, String description, String thumbnailUrl) {
        this.name = name;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    public void setUser(User user2) {
        this.user = user2;
    }

    // getters & setters
}
