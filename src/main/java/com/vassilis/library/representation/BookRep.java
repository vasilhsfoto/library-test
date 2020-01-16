package com.vassilis.library.representation;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class BookRep {

    @Data
    public static class AuthorRep {
        @NotBlank
        private String name;
    }

    private String id;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate publicationDate;

    private Long numOfPages;

    @NotEmpty
    @Valid
    private List<AuthorRep> authors;
}
