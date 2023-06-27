package com.example.book.features.mongo.application.mapper;

import com.example.book.features.mongo.domain.Book;
import com.example.book.features.mongo.domain.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO createBookDTO(Book book);

    Book bookDTOToBook(BookDTO bookDTO);

}
