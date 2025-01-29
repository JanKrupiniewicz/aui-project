package com.example.demo.components;

import com.example.demo.entities.Author;
import com.example.demo.services.AuthorService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer {

    private final AuthorService authorService;

    @Autowired
    public DataInitializer(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostConstruct
    public void init() {
        Author shakespear = Author.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000001")).name("William").surname("Shakespeare").build();
        Author cervantes = Author.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000002")).name("Miguel").surname("de Cervantes").build();
        Author tolstoy = Author.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000003")).name("Leo").surname("Tolstoy").build();
        Author dostoevsky = Author.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000004")).name("Fyodor").surname("Dostoevsky").build();

        authorService.createAuthor(shakespear);
        authorService.createAuthor(cervantes);
        authorService.createAuthor(tolstoy);
        authorService.createAuthor(dostoevsky);

        System.out.println("Data initialized");
    }
}


