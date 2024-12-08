package com.example.demo.dto;

import com.example.demo.dto.book.BookCollectionDTO;
import com.example.demo.dto.book.CreateBookDTO;
import com.example.demo.dto.book.ReadBookDTO;
import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.services.AuthorService;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class Mapper {
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public Mapper(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    public UUID mapToCreateBookDTO(CreateBookDTO createBookDTO) {
        Author author = authorService.getAuthor(createBookDTO.getAuthorId());
        UUID id = UUID.randomUUID();
        if (author != null) {
            Book book = Book.builder()
                    .id(id)
                    .title(createBookDTO.getTitle())
                    .author(author)
                    .build();
            bookService.createBook(book);
            return id;
        } else {
            throw new IllegalArgumentException("Author not found");
        }
    }

    public void mapToUpdateBookDTO(UUID id, CreateBookDTO createBookDTO) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            book.setTitle(createBookDTO.getTitle());
            bookService.updateBook(id, book);
        } else {
            throw new IllegalArgumentException("Author not found");
        }
    }

    public List<BookCollectionDTO> mapToReadBooksDTO() {
        List<Book> books = bookService.getAllBooks();
        List<BookCollectionDTO> bookCollectionDTOs = new ArrayList<>();
        for (Book book : books) {
            BookCollectionDTO bookCollectionDTO = BookCollectionDTO.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .build();
            bookCollectionDTOs.add(bookCollectionDTO);
        }
        return bookCollectionDTOs;
    }

    public ReadBookDTO mapToReadBookDTO(UUID id) {
        Book book = bookService.getBookById(id);
        ReadBookDTO readBookDTO = ReadBookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(Author.builder()
                        .id(book.getAuthor().getId())
                        .build())
                .build();
        return readBookDTO;
    }

    public List<BookCollectionDTO> mapToReadAuthorBooksDTO(UUID id) {
        List<Book> authorsBooks = bookService.getBooksByAuthor(id);
        List<BookCollectionDTO> bookCollectionDTOs = new ArrayList<>();
        for (Book book : authorsBooks) {
            BookCollectionDTO bookCollectionDTO = BookCollectionDTO.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .build();
            bookCollectionDTOs.add(bookCollectionDTO);
        }
        return bookCollectionDTOs;
    }
}
