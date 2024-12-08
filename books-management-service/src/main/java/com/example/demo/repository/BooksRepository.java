package com.example.demo.repository;

import com.example.demo.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BooksRepository extends JpaRepository<Book, UUID> {
    List<Book> findByAuthorId(UUID authorId);

    Book findByTitle(String deleteBookTitle);
}

