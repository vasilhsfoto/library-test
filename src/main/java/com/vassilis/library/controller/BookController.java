package com.vassilis.library.controller;

import com.vassilis.library.representation.BookDto;
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
    public ResponseEntity<BookDto> createBook(
            @PathVariable String libraryId,
            @RequestBody @Valid BookDto bookDto) {
        log.info("Create method called with payload {} ", bookDto);

        BookDto result = bookService.createBook(libraryId, bookDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @GetMapping("/api/libraries/{libraryId}/books/{bookId}")
    public ResponseEntity<BookDto> getBookById(
            @PathVariable String libraryId,
            @PathVariable String bookId) {

        log.info("Get method called with id {} ", bookId);
        BookDto result = bookService.getBookById(bookId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/api/libraries/{libraryId}/books")
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> bookDtos = bookService.getBooks();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookDtos);
    }
}
