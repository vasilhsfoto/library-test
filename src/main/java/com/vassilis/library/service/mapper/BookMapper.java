package com.vassilis.library.service.mapper;

import com.vassilis.library.model.Book;
import com.vassilis.library.representation.BookRep;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public abstract class BookMapper {

    @Mappings(
            @Mapping(target = "id", expression = "java( java.util.UUID.randomUUID().toString() )")
    )
    public abstract Book toBook(BookRep bookRep, String libraryId);

    public abstract BookRep toBookRep(Book book);

    public abstract List<BookRep> toListBookRep(List<Book> books);
}
