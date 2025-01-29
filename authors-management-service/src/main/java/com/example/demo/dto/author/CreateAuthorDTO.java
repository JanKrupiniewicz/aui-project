package com.example.demo.dto.author;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAuthorDTO {
    private String name;
    private String surname;
}
