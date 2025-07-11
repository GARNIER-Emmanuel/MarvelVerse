package com.manu.marvel.response;

import java.util.List;

public class MarvelSerieResponse {
    private Data data;

    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }

    public static class Data {
        private List<SerieResult> results;

        public List<SerieResult> getResults() { return results; }
        public void setResults(List<SerieResult> results) { this.results = results; }
    }

    public static class SerieResult {
        private Long id;
        private String title;
        private String description;
        private Thumbnail thumbnail;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public Thumbnail getThumbnail() { return thumbnail; }
        public void setThumbnail(Thumbnail thumbnail) { this.thumbnail = thumbnail; }

        public static class Thumbnail {
            private String path;
            private String extension;

            public String getPath() { return path; }
            public void setPath(String path) { this.path = path; }

            public String getExtension() { return extension; }
            public void setExtension(String extension) { this.extension = extension; }
        }
    }
}
