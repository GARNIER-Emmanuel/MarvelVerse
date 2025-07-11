package com.manu.marvel.service;

import com.manu.marvel.dto.MarvelSerieResponse;
import com.manu.marvel.model.Serie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarvelSerieService {

    @Value("${marvel.api.base-url}")
    private String baseUrl;

    @Value("${marvel.api.public-key}")
    private String publicKey;

    @Value("${marvel.api.ts}")
    private String ts;

    @Value("${marvel.api.hash}")
    private String hash;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Serie> getSeries(String title) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/series")
                .queryParam("apikey", publicKey)
                .queryParam("ts", ts)
                .queryParam("hash", hash)
                .queryParam("limit", 10);

        if (title != null && !title.isEmpty()) {
            uriBuilder.queryParam("titleStartsWith", title);
        }

        String uri = uriBuilder.toUriString();
        MarvelSerieResponse response = restTemplate.getForObject(uri, MarvelSerieResponse.class);

        if (response == null || response.getData() == null) return List.of();

        return response.getData().getResults().stream().map(result -> {
            String thumbnailUrl = result.getThumbnail().getPath() + "." + result.getThumbnail().getExtension();
            return new Serie(result.getId(), result.getTitle(), result.getDescription(), thumbnailUrl);
        }).collect(Collectors.toList());
    }
}
