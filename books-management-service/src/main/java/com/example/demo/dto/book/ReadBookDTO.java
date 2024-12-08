package com.example.demo.dto.book;

import com.example.demo.entities.Author;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadBookDTO {
    private UUID id;
    private String title;
    private Author author;
}
