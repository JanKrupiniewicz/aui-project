package com.example.demo.dto.book;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCollectionDTO {
    private UUID id;
    private String title;
}
