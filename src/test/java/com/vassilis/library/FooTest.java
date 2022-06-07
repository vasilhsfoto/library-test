package com.vassilis.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vassilis.library.representation.BookDto;

@SpringBootTest
public class FooTest {

    @Autowired
    private ObjectMapper mapper;

    public static class Animal {
        protected String shout() {
            return "animal is shouting";
        }
    }

    @Test
    void test() throws JsonProcessingException {
        var str = """
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
        var bookDto = mapper.readValue(str, BookDto.class);
        System.out.println(bookDto);
    }

    @Test
    void test2() {
        String query = """
                    SELECT *
                    FROM ORDER
                """;
    }
}
