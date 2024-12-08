package com.example.demo.controllers;

import com.example.demo.dto.Mapper;
import com.example.demo.dto.book.BookCollectionDTO;
import com.example.demo.dto.book.CreateBookDTO;
import com.example.demo.dto.book.ReadBookDTO;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/books")
public class BookController {
    BookService bookService;
    Mapper mapper;

    @Autowired
    public BookController(BookService bookService, Mapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @PutMapping
    public ResponseEntity<UUID> createBook(@RequestBody CreateBookDTO createBookDTO) {
        UUID id = mapper.mapToCreateBookDTO(createBookDTO);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReadBookDTO> updateBook(@PathVariable UUID id, @RequestBody CreateBookDTO createBookDTO) {
        mapper.mapToUpdateBookDTO(id, createBookDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BookCollectionDTO>> getBooks() {
        List<BookCollectionDTO> books = mapper.mapToReadBooksDTO();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadBookDTO> getBook(@PathVariable UUID id) {
        ReadBookDTO bookDTO = mapper.mapToReadBookDTO(id);
        if (bookDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<BookCollectionDTO>> getAuthorBooks(@PathVariable UUID id) {
        List<BookCollectionDTO> books = mapper.mapToReadAuthorBooksDTO(id);
        return ResponseEntity.ok(books);
    }
}
