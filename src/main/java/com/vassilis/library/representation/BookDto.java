package com.vassilis.library.representation;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class BookDto {

    @Data
    public static class AuthorDto {
        @NotBlank
        private String name;
        @NotNull
        private LocalDate birthDate;
    }

    private String id;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate publicationDate;

    private Long numOfPages;

    @NotNull
    private Double price;

    @NotEmpty
    @Valid
    private List<AuthorDto> authors;
}
