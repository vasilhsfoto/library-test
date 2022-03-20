package com.vassilis.library.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vassilis.library.aspect.TimeExecution;
import com.vassilis.library.exception.WebAppException;
import com.vassilis.library.exception.WebAppExceptionFactory;
import com.vassilis.library.model.Book;
import com.vassilis.library.repository.BookRepository;
import com.vassilis.library.representation.BookDto;
import com.vassilis.library.service.mapper.BookMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    @TimeExecution
    public BookDto createBook(String libraryId, BookDto bookDto) {
        validatePayload(bookDto);

        var book = bookMapper.toBook(bookDto, libraryId);
        Book bookSaved = bookRepository.save(book);
        return bookMapper.toBookDto(bookSaved);
    }

    @TimeExecution
    public BookDto getBookById(String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        WebAppExceptionFactory.getNotFoundError(String.format("Book with `%s` not found", bookId)));

        return bookMapper.toBookDto(book);
    }

    //TODO: make meta to be set up correctly in the DB - currently MapStruct doesn't construct it
    @TimeExecution
    public List<BookDto> getBooks() {
        List<Book> books = bookRepository.findAll();

        return (bookMapper.toListBookRep(books));
    }

    private void validatePayload(BookDto bookDto) {
        if (bookDto.getId() != null) {
            throw new WebAppException("ID of created entities must not provided in the payload",
                    HttpStatus.BAD_REQUEST);
        }
    }

}
