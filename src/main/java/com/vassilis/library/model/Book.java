package com.vassilis.library.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Book {

    @Data
    public static class Author {
        private String name;
    }

    private String id;
    private String name;
    private LocalDate publicationDate;
    private Long numOfPages;
    private List<Author> authors;
    private Double price;
    private String libraryId;
}
