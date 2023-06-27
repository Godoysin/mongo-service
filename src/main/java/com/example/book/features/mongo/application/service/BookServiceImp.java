package com.example.book.features.mongo.application.service;

import com.example.book.features.mongo.application.mapper.BookMapper;
import com.example.book.features.mongo.domain.BookDTO;
import com.example.book.features.mongo.infrastructure.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImp implements BookService {

    private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    BookServiceImp(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO findByIdService(Integer id) {
        return bookMapper.createBookDTO(bookRepository.findById(id).orElseThrow());
    }

    @Override
    public void addNewBook(BookDTO bookDTO) {
        bookRepository.save(bookMapper.bookDTOToBook(bookDTO));
    }
}
