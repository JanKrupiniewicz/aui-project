package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "books")
public class Book {

    @Id
    private UUID id;

    private String title;

    @JoinColumn(name = "author_id", nullable = false)
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Author author;

    public static MyBookBuilder builder() {
        return new MyBookBuilder();
    }

    public static class MyBookBuilder extends BookBuilder {
        @Override
        public Book build() {
            if (super.author == null) {
                throw new IllegalStateException("Author is required");
            }
            var book = super.build();
            if (super.author.getBooks() != null) {
                super.author.getBooks().add(book);
            }
            return book;
        }
    }
}
