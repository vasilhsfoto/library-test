package com.vassilis.library.repository;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;

import com.vassilis.library.configuration.Profiles;
import com.vassilis.library.model.Book;

@Profile(Profiles.CB_PROFILE)
public interface BookRepository extends CouchbaseRepository<Book, String> {

    @Query("#{#n1ql.selectEntity} WHERE _meta.type = 'BOOK'")
    List<Book> findAll();
}
