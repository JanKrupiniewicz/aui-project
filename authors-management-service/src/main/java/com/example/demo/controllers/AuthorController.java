package com.example.demo.controllers;

import com.example.demo.dto.Mapper;
import com.example.demo.dto.author.CreateAuthorDTO;
import com.example.demo.dto.author.ReadAuthorDTO;
import com.example.demo.services.AuthorService;
import com.example.demo.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private AuthorService authorService;
    private BookService bookService;
    private Mapper mapper;

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService, Mapper mapper) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UUID> createAuthor(@RequestBody CreateAuthorDTO createAuthorDTO) {
        UUID id = mapper.mapToCreateAuthorDTO(createAuthorDTO);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReadAuthorDTO> updateAuthor(@PathVariable UUID id, @RequestBody CreateAuthorDTO createAuthorDTO) {
        mapper.mapToUpdateAuthorDTO(id, createAuthorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable UUID id) {
        authorService.deleteAuthor(id);
        bookService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReadAuthorDTO>> getAuthors() {
        List<ReadAuthorDTO> authors = mapper.mapAuthorsToDTOs(authorService.getAllAuthors());
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadAuthorDTO> getAuthor(@PathVariable UUID id) {
        ReadAuthorDTO authorDTO = mapper.mapToReadAuthorDTO(id);
        if (authorDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authorDTO);
    }
}
