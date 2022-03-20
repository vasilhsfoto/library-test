package com.vassilis.library.service.mapper;

import java.util.List;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.vassilis.library.model.Book;
import com.vassilis.library.representation.BookDto;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public abstract class BookMapper {

    @Mappings({
            @Mapping(target = "id", expression = "java( java.util.UUID.randomUUID().toString() )")
    })
    public abstract Book toBook(BookDto bookDto, String libraryId);

    public abstract BookDto toBookDto(Book book);

    public abstract List<BookDto> toListBookRep(List<Book> books);
}
