package com.example.demo.dto;

import com.example.demo.dto.author.CreateAuthorDTO;
import com.example.demo.dto.author.ReadAuthorDTO;
import com.example.demo.entities.Author;
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

    public UUID mapToCreateAuthorDTO(CreateAuthorDTO authorDTO) {
        UUID id = UUID.randomUUID();

        Author author = Author.builder()
                .id(id)
                .name(authorDTO.getName())
                .surname(authorDTO.getSurname())
                .build();

        bookService.addAuthor(id);
        authorService.createAuthor(author);
        return id;
    }

    public ReadAuthorDTO mapToReadAuthorDTO(UUID id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return null;
        }
        return ReadAuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

    public void mapToUpdateAuthorDTO(UUID id, CreateAuthorDTO createAuthorDTO) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            author.setName(createAuthorDTO.getName());
            author.setSurname(createAuthorDTO.getSurname());
            authorService.updateAuthor(id, author);
        }
    }

//    public List<BookCollectionDTO> mapToReadAuthorBooksDTO(UUID id) {
//        Author author = authorService.getAuthorByIdByJoin(id);
//        List<BookCollectionDTO> bookCollectionDTOs = new ArrayList<>();
//
//        if (author != null && author.getBooks() != null) {
//            for (Book book : author.getBooks()) {
//                BookCollectionDTO bookCollectionDTO = BookCollectionDTO.builder()
//                        .title(book.getTitle())
//                        .build();
//                bookCollectionDTOs.add(bookCollectionDTO);
//            }
//        }
//        return bookCollectionDTOs;
//    }

    public List<ReadAuthorDTO> mapAuthorsToDTOs(List<Author> authors) {
        List<ReadAuthorDTO> dtos = new ArrayList<>();
        for (Author author : authors) {
            dtos.add(ReadAuthorDTO.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .surname(author.getSurname())
                    .build());
        }
        return dtos;
    }
}
