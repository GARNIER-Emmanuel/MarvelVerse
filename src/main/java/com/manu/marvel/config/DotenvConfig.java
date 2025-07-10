package com.manu.marvel.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DotenvConfig {

    int test;

    @PostConstruct
    public void init() {
        Dotenv dotenv = Dotenv.load();

        System.setProperty("MARVEL_PUBLIC_KEY", Objects.requireNonNull(dotenv.get("MARVEL_PUBLIC_KEY")));
        System.setProperty("MARVEL_PRIVATE_KEY", Objects.requireNonNull(dotenv.get("MARVEL_PRIVATE_KEY")));

        // Either change these to match your .env file names:
        System.setProperty("DB_URL", Objects.requireNonNull(dotenv.get("DB_URL")));
        System.setProperty("DB_USER", Objects.requireNonNull(dotenv.get("DB_USER")));
        System.setProperty("DB_PASSWORD", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
    }
}
