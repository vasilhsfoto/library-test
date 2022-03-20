package com.vassilis.library.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Profile;
import org.springframework.data.couchbase.core.CouchbaseOperations;
import org.springframework.data.couchbase.repository.query.CouchbaseEntityInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import com.vassilis.library.configuration.Profiles;
import com.vassilis.library.model.Book;

@Repository
@Profile(Profiles.MEM_PROFILE)
public class BookRepositoryMem implements BookRepository {

    private final Map<String, Book> idToBook = new ConcurrentHashMap<>();

    @Override
    public Book save(Book book) {
        idToBook.put(book.getId(), book);
        return book;
    }

    @Override
    public Optional<Book> findById(String bookId) {
        return Optional.ofNullable(idToBook.get(bookId));
    }

    @Override
    public List<Book> findAll() {
        Collection<Book> books = idToBook.values();
        return List.of(books.toArray(new Book[] {}));
    }

    @Override
    public <S extends Book> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Book> findAll(Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Book> findAll(QueryScanConsistency queryScanConsistency) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Book> findAllById(Iterable<String> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Book entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Book> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CouchbaseEntityInformation<Book, String> getEntityInformation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CouchbaseOperations getOperations() {
        throw new UnsupportedOperationException();
    }
}
