package com.example.demo.services;

import com.example.demo.entities.Author;
import com.example.demo.repository.AuthorsRepository;
import jakarta.transaction.Transactional;
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

    public void createAuthor(Author author) {
        authorsRepository.save(author);
    }

    public Author getAuthorById(UUID id) {
        return authorsRepository.findById(id).orElse(null);
    }

    public List<Author> getAllAuthors() {
        return authorsRepository.findAll();
    }

    public void deleteAuthor(UUID id) {
        authorsRepository.deleteById(id);
    }

    public void updateAuthor(UUID id, Author author) {
        author.setId(id);
        authorsRepository.save(author);
    }

    @Transactional
    public Author getAuthorByNameAndSurname(String name, String surname) {
        return authorsRepository.findByNameAndSurname(name, surname);
    }
}
