package com.vassilis.library.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vassilis.library.JunitTags;
import com.vassilis.library.representation.BookDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag(JunitTags.API_TEST)
@SpringBootTest
@AutoConfigureMockMvc
public class BookApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void beforeEach() {
        System.out.println("====== Before the test method");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("====== After the test method");
    }

    @Test
    void createBook_addNewBook_whenBookIsProvided() throws Exception {
        var body = """
                {
                   "name":"book-name",
                   "publicationDate":"1998-10-12",
                   "numOfPages": 150,
                   "authors":[
                        {
                            "name":"Alex",
                            "birthDate":"1908-12-10"
                        }
                    ],
                    "price": 99.9
                }
                """;
        MvcResult mvcResult = mockMvc.perform(post("/api/libraries/1/books")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(body))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();

        BookDto newBook = mapper.readValue(mvcResult.getResponse().getContentAsString(), BookDto.class);
        String bookId = newBook.getId();

        mockMvc.perform(get("/api/libraries/1/books/{bookId}", bookId))
                .andExpect(content().json(body));
    }
}
