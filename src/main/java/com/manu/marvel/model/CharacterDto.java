package com.manu.marvel.model;

public class CharacterDto {
    private int id;
    private String name;
    private String description;
    private String thumbnailUrl;

    public CharacterDto(int id, String name, String description, String thumbnailUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getThumbnailUrl() { return thumbnailUrl; }
}
