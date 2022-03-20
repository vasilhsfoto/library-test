package com.vassilis.library.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vassilis.library.repository.BookRepository;
import com.vassilis.library.representation.BookDto;
import com.vassilis.library.representation.BookDto.AuthorDto;
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
        BookDto bookDto = new BookDto();
        bookDto.setName("book-name");
        bookDto.setNumOfPages(10L);
        bookDto.setPublicationDate(LocalDate.of(1998, 10, 12));

        BookDto.AuthorDto authorDto1 = new BookDto.AuthorDto();
        authorDto1.setName("Alex");
        AuthorDto authorDto2 = new AuthorDto();
        authorDto2.setName("Alex");

        bookDto.setAuthors(List.of(authorDto1, authorDto2));

        System.out.println(mapper.writeValueAsString(bookDto));
    }
}