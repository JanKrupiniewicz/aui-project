package com.example.demo.services;

import com.example.demo.entities.Author;
import com.example.demo.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class AuthorService {
    private final AuthorsRepository authorsRepository;

    @Autowired
    public AuthorService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public void createAuthor(UUID id) {
        if(authorsRepository.findById(id).isPresent()) {
            throw new IllegalStateException();
        }
        authorsRepository.save(Author.builder().id(id).build());
    }

    public Author getAuthor(UUID id) {
        return authorsRepository.findById(id).orElse(null);
    }

    public void deleteAuthor(UUID id) {
        authorsRepository.deleteById(id);
    }

    public List<Author> getAllAuthors() {
        return authorsRepository.findAll();
    }
}
