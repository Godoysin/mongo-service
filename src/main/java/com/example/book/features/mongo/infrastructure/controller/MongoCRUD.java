package com.example.book.features.mongo.infrastructure.controller;

import com.example.book.features.mongo.application.service.BookService;
import com.example.book.features.mongo.domain.BookDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class MongoCRUD {

    private final BookService bookService;

    public MongoCRUD(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book-by-id")
    public BookDTO bookByIdInterface(@RequestParam(value = "id") Integer id) {
        return bookService.findByIdService(id);
    }

    @PostMapping("/book")
    public void addNewBook(@RequestBody BookDTO bookDTO) {
        bookService.addNewBook(bookDTO);
    }

}
