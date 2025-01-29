package com.example.demo.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BookService {

    private final String bookServiceUrl = "http://localhost:8081";

    private final RestTemplate restTemplate;

    public void deleteAuthor(UUID authorId) {
        try {
            restTemplate.delete(bookServiceUrl + "/api/books/authors/" + authorId);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void addAuthor(UUID authorId) {
        try {
            restTemplate.put(bookServiceUrl + "/api/books/authors", authorId);
        } catch (Exception e) {
            System.err.println("Error during POST request: " + e.getMessage());
        }
    }
}
