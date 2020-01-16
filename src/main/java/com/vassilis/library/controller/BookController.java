package com.vassilis.library.controller;

import com.vassilis.library.representation.BookRep;
import com.vassilis.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @PostMapping("/api/libraries/{libraryId}/books")
    public ResponseEntity<BookRep> createBook(
            @PathVariable String libraryId,
            @RequestBody @Valid BookRep bookRep) {
        log.info("Create method called with payload {} ", bookRep);

        BookRep result = bookService.createBook(libraryId, bookRep);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @GetMapping("/api/libraries/{libraryId}/books/{bookId}")
    public ResponseEntity<BookRep> getBookById(
            @PathVariable String libraryId,
            @PathVariable String bookId) {

        log.info("Get method called with id {} ", bookId);
        BookRep result = bookService.getBookById(bookId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/api/libraries/{libraryId}/books")
    public ResponseEntity<List<BookRep>> getBooks() {
        List<BookRep> bookReps = bookService.getBooks();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookReps);
    }
}
