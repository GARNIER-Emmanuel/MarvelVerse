package com.manu.marvel.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelResponse {
    private int code;
    private String status;
    private Data data;

    // Getters & Setters

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private List<Character> results;

        public List<Character> getResults() { return results; }
        public void setResults(List<Character> results) { this.results = results; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Character {
        private int id;
        private String name;
        private String description;
        private Thumbnail thumbnail;

        // Getters & Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public Thumbnail getThumbnail() { return thumbnail; }
        public void setThumbnail(Thumbnail thumbnail) { this.thumbnail = thumbnail; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Thumbnail {
        private String path;
        private String extension;

        // Getters & Setters
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }

        public String getExtension() { return extension; }
        public void setExtension(String extension) { this.extension = extension; }
    }



}
