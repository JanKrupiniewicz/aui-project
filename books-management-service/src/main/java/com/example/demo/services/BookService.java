package com.example.demo.services;

import com.example.demo.entities.Book;
import com.example.demo.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class BookService {
    private final BooksRepository booksRepository;

    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Book createBook(Book book) {
        return booksRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    public Book getBookByTitle(String deleteBookTitle) {
        return booksRepository.findByTitle(deleteBookTitle);
    }

    public Book getBookById(UUID id) {
        return booksRepository.findById(id).orElse(null);
    }

    public void deleteBook(Book deleteBook) {
        booksRepository.delete(deleteBook);
    }

    public void deleteBookById(UUID id) {
        booksRepository.deleteById(id);
    }

    public void updateBook(UUID id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    public List<Book> getBooksByAuthor(UUID id) {
        return booksRepository.findByAuthorId(id);
    }
}

