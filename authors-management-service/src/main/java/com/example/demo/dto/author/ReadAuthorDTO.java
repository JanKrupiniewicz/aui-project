package com.example.demo.dto.author;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadAuthorDTO {
    private UUID id;
    private String name;
    private String surname;
}
