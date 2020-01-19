package com.vassilis.library.repository;

import com.vassilis.library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookRepository {

    private final Map<String, Book> idToBook = new ConcurrentHashMap<>();

    public Book addBook(Book book) {
        idToBook.put(book.getId(), book);
        return book;
    }

    public Book getBookById(String bookId) {
        return idToBook.get(bookId);
    }

    public List<Book> getBooks() {
        Collection<Book> books = idToBook.values();
        return List.of(books.toArray(new Book[]{}));
    }
}
