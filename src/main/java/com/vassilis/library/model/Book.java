package com.vassilis.library.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
public class Book {

    @Data
    public static class Author {
        private String name;
        private LocalDate birthDate;
    }

    @Data
    public static class Meta {
        private String type;
    }

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private LocalDate publicationDate;

    @Field
    private Long numOfPages;

    @Field
    private List<Author> authors;

    @Field
    private Double price;

    @Field
    private String libraryId;

    @Field("_meta")
    private Meta meta;
}
