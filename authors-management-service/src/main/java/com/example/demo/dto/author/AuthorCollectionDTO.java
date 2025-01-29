package com.example.demo.dto.author;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorCollectionDTO {
    private UUID id;
    private String surname;
}
