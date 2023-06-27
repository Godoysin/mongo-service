package com.example.book.features.mongo.application.service;

import com.example.book.features.mongo.domain.BookDTO;

public interface BookService {

    BookDTO findByIdService(Integer id);

    void addNewBook(BookDTO bookDTO);

}
