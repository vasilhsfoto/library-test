package com.vassilis.library.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vassilis.library.repository.BookRepository;
import com.vassilis.library.representation.BookRep;
import com.vassilis.library.representation.BookRep.AuthorRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class BookServiceTest {

    @Mock
    private BookRepository bookRepositoryMocked;

    @InjectMocks
    private BookService bookService;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createBook() throws JsonProcessingException {
        BookRep bookRep = new BookRep();
        bookRep.setName("book-name");
        bookRep.setNumOfPages(10L);
        bookRep.setPublicationDate(LocalDate.of(1998, 10, 12));

        AuthorRep authorRep1 = new AuthorRep();
        authorRep1.setName("Alex");
        AuthorRep authorRep2 = new AuthorRep();
        authorRep2.setName("Alex");

        bookRep.setAuthors(List.of(authorRep1, authorRep2));

        System.out.println(mapper.writeValueAsString(bookRep));
    }
}