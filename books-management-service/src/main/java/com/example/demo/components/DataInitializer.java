package com.example.demo.components;

import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.services.AuthorService;
import com.example.demo.services.BookService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer {

    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public DataInitializer(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PostConstruct
    public void init() {
        UUID shakespear = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID cervantes = UUID.fromString("00000000-0000-0000-0000-000000000002");
        UUID tolstoy = UUID.fromString("00000000-0000-0000-0000-000000000003");
        UUID dostoevsky = UUID.fromString("00000000-0000-0000-0000-000000000004");

        Book hamlet = Book.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000001")).title("Hamlet").author(Author.builder().id(shakespear).build()).build();
        Book othello = Book.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000002")).title("Othello").author(Author.builder().id(shakespear).build()).build();
        Book donQuixote = Book.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000003")).title("Don Quixote").author(Author.builder().id(cervantes).build()).build();
        Book ingeniousGentleman = Book.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000004")).title("The Ingenious Gentleman Don Quixote of La Mancha").author(Author.builder().id(cervantes).build()).build();
        Book warAndPeace = Book.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000005")).title("War and Peace").author(Author.builder().id(tolstoy).build()).build();
        Book annaKarenina = Book.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000006")).title("Anna Karenina").author(Author.builder().id(tolstoy).build()).build();
        Book crimeAndPunishment = Book.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000007")).title("Crime and Punishment").author(Author.builder().id(dostoevsky).build()).build();
        Book brothersKaramazov = Book.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000008")).title("The Brothers Karamazov").author(Author.builder().id(dostoevsky).build()).build();

        authorService.createAuthor(shakespear);
        authorService.createAuthor(cervantes);
        authorService.createAuthor(tolstoy);
        authorService.createAuthor(dostoevsky);

        bookService.createBook(hamlet);
        bookService.createBook(othello);
        bookService.createBook(donQuixote);
        bookService.createBook(ingeniousGentleman);
        bookService.createBook(warAndPeace);
        bookService.createBook(annaKarenina);
        bookService.createBook(crimeAndPunishment);
        bookService.createBook(brothersKaramazov);

        System.out.println("Data initialized");
    }
}


