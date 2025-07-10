package com.manu.marvel.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manu.marvel.model.CharacterDto;
import com.manu.marvel.model.ComicDto;
import com.manu.marvel.model.MarvelComicsResponse;
import com.manu.marvel.model.MarvelResponse;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarvelApiService {

    private final String publicKey = System.getProperty("MARVEL_PUBLIC_KEY");
    private final String privateKey = System.getProperty("MARVEL_PRIVATE_KEY");
    private final String baseUrl = "https://gateway.marvel.com/v1/public";

    public MarvelResponse getCharacters() {
        try {
            String ts = Long.toString(System.currentTimeMillis());
            String hash = generateHash(ts, privateKey, publicKey);

            String url = baseUrl + "/characters?limit=10&ts=" + ts + "&apikey=" + publicKey + "&hash=" + hash;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();



            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            ObjectMapper mapper = new ObjectMapper();
            MarvelResponse marvelResponse = mapper.readValue(json, MarvelResponse.class);

            return marvelResponse;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String generateHash(String ts, String privateKey, String publicKey) throws Exception {
        String value = ts + privateKey + publicKey;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(value.getBytes());
        StringBuilder hash = new StringBuilder();
        for (byte b : digest) {
            hash.append(String.format("%02x", b));
        }
        return hash.toString();
    }

    public List<CharacterDto> getCharacterDtos() {
        try {
            // Utiliser la méthode existante pour obtenir la réponse
            MarvelResponse marvelResponse = getCharacters();

            if (marvelResponse == null) {
                return Collections.emptyList();
            }

            // Transformer la liste
            return marvelResponse.getData().getResults().stream().map(character -> {
                String thumbnailUrl = character.getThumbnail().getPath() + "." + character.getThumbnail().getExtension();
                return new CharacterDto(
                    character.getId(),
                    character.getName(),
                    character.getDescription(),
                    thumbnailUrl
                );
            }).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public CharacterDto getCharacterById(int id) {
        try {

            String timestamp = String.valueOf(System.currentTimeMillis());
            String hash = generateHash(timestamp, privateKey, publicKey);
            String requestUrl = baseUrl + "/characters/" + id + "?ts=" + timestamp + "&apikey=" + publicKey + "&hash=" + hash;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestUrl))
                    .GET()
                    .build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            MarvelResponse marvelResponse = mapper.readValue(response.body(), MarvelResponse.class);

            MarvelResponse.Character character = marvelResponse.getData().getResults().get(0);

            String thumbnailUrl = character.getThumbnail().getPath() + "." + character.getThumbnail().getExtension();

            return new CharacterDto(
                    character.getId(),
                    character.getName(),
                    character.getDescription(),
                    thumbnailUrl
            );

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CharacterDto> searchCharactersByName(String name) {
        List<CharacterDto> characterList = new ArrayList<>();

        try {

            String timestamp = String.valueOf(System.currentTimeMillis());
            String hash = generateHash(timestamp, privateKey, publicKey);

            String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
            String requestUrl = baseUrl + "/characters?name=" + encodedName
                    + "&ts=" + timestamp + "&apikey=" + publicKey + "&hash=" + hash;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            MarvelResponse marvelResponse = mapper.readValue(response.body(), MarvelResponse.class);

            for (MarvelResponse.Character character : marvelResponse.getData().getResults()) {
                String thumbnailUrl = character.getThumbnail().getPath() + "." + character.getThumbnail().getExtension();
                characterList.add(new CharacterDto(
                        character.getId(),
                        character.getName(),
                        character.getDescription(),
                        thumbnailUrl
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return characterList;
    }

    public List<CharacterDto> getAllCharacters(int limit, int offset) {
        List<CharacterDto> characterList = new ArrayList<>();

        try {

            String timestamp = String.valueOf(System.currentTimeMillis());
            String hash = generateHash(timestamp, privateKey, publicKey);
            String requestUrl = baseUrl + "/characters?limit=" + limit + "&offset=" + offset
                    + "&ts=" + timestamp + "&apikey=" + publicKey + "&hash=" + hash;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestUrl))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            MarvelResponse marvelResponse = mapper.readValue(response.body(), MarvelResponse.class);

            for (MarvelResponse.Character character : marvelResponse.getData().getResults()) {                String thumbnailUrl = character.getThumbnail().getPath() + "." + character.getThumbnail().getExtension();
                characterList.add(new CharacterDto(
                        character.getId(),
                        character.getName(),
                        character.getDescription(),
                        thumbnailUrl
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return characterList;
    }

    public List<ComicDto> getComicsByCharacterId(int characterId) {
        List<ComicDto> comicsList = new ArrayList<>();

        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String hash = generateHash(timestamp, privateKey, publicKey);
            String requestUrl = baseUrl + "/characters/" + characterId + "/comics"
                    + "?ts=" + timestamp + "&apikey=" + publicKey + "&hash=" + hash;

            System.out.println("Request URL: " + requestUrl);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestUrl))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());

            ObjectMapper mapper = new ObjectMapper();
            MarvelComicsResponse comicsResponse = mapper.readValue(response.body(), MarvelComicsResponse.class);

            if (comicsResponse.getData() != null && comicsResponse.getData().getResults() != null) {
                System.out.println("Number of comics found: " + comicsResponse.getData().getResults().size());

                for (MarvelComicsResponse.ComicResult comic : comicsResponse.getData().getResults()) {
                    String thumbnailUrl = comic.getThumbnail().getPath() + "." + comic.getThumbnail().getExtension();
                    comicsList.add(new ComicDto(
                            comic.getTitle(),
                            comic.getDescription(),
                            thumbnailUrl
                    ));
                }
            } else {
                System.out.println("No comics data found in the response");
            }

        } catch (Exception e) {
            System.out.println("Error getting comics for character ID " + characterId + ": " + e.getMessage());
            e.printStackTrace();
        }

        return comicsList;
    }

    public ComicDto getComicById(int comicId) {
        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String hash = generateHash(timestamp, privateKey, publicKey);
            String requestUrl = baseUrl + "/comics/" + comicId
                    + "?ts=" + timestamp + "&apikey=" + publicKey + "&hash=" + hash;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestUrl))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            MarvelComicsResponse comicResponse = mapper.readValue(response.body(), MarvelComicsResponse.class);

            MarvelComicsResponse.ComicResult comic = comicResponse.getData().getResults().get(0);

            String thumbnailUrl = comic.getThumbnail().getPath() + "." + comic.getThumbnail().getExtension();

            return new ComicDto(
                    comic.getTitle(),
                    comic.getDescription(),
                    thumbnailUrl
            );

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ComicDto> searchComicsByTitle(String title) {
        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String hash = generateHash(timestamp, privateKey, publicKey);
            String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
            String requestUrl = baseUrl + "/comics"
                    + "?titleStartsWith=" + encodedTitle
                    + "&ts=" + timestamp + "&apikey=" + publicKey + "&hash=" + hash;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestUrl))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            MarvelComicsResponse comicResponse = mapper.readValue(response.body(), MarvelComicsResponse.class);

            List<ComicDto> comics = new ArrayList<>();

            for (MarvelComicsResponse.ComicResult comic : comicResponse.getData().getResults()) {
                String thumbnailUrl = comic.getThumbnail().getPath() + "." + comic.getThumbnail().getExtension();
                comics.add(new ComicDto(
                        comic.getTitle(),
                        comic.getDescription(),
                        thumbnailUrl
                ));
            }

            return comics;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}
