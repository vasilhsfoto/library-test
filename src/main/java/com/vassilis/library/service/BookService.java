package com.vassilis.library.service;

import com.vassilis.library.aspect.TimeExecution;
import com.vassilis.library.exception.WebAppException;
import com.vassilis.library.exception.WebAppExceptionFactory;
import com.vassilis.library.model.Book;
import com.vassilis.library.repository.BookRepository;
import com.vassilis.library.representation.BookRep;
import com.vassilis.library.service.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    @TimeExecution
    public BookRep createBook(String libraryId, BookRep bookRep) {
        validatePayload(bookRep);

        var book = bookMapper.toBook(bookRep, libraryId);
        Book bookSaved = bookRepository.addBook(book);
        return bookMapper.toBookRep(bookSaved);
    }

    @TimeExecution
    public BookRep getBookById(String bookId) {
        Book book = Optional.ofNullable(bookRepository.getBookById(bookId))
                .orElseThrow(() ->
                        WebAppExceptionFactory.getNotFoundError(String.format("Book with `%s` not found", bookId)));

        return bookMapper.toBookRep(book);
    }

    @TimeExecution
    public List<BookRep> getBooks() {
        List<Book> books = bookRepository.getBooks();

        return (bookMapper.toListBookRep(books));
    }

    private void validatePayload(BookRep bookRep) {
        if (bookRep.getId() != null) {
            throw new WebAppException("ID of created entities must not provided in the payload", HttpStatus.BAD_REQUEST);
        }
    }

}
