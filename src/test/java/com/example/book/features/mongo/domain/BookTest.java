package com.example.book.features.mongo.domain;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    @NotNull
    public static Book getTestBook() {
        int id = 1;
        String author = "author";
        String title = "title";
        String genre = "genre";
        Instant date = Instant.now();
        String isbn = "isbn";

        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setPublicationDate(date);
        book.setIsbn(isbn);

        return book;
    }

    @Test
    void shouldValidateBookSetterAndGetter() {
        // given
        Book testBook = getTestBook();

        // when
        Book book = new Book();
        book.setId(testBook.getId());
        book.setTitle(testBook.getTitle());
        book.setAuthor(testBook.getAuthor());
        book.setGenre(testBook.getGenre());
        book.setPublicationDate(testBook.getPublicationDate());
        book.setIsbn(testBook.getIsbn());

        // then
        assertAll(
                "Asserting all book fields",
                () -> assertEquals(testBook.getId(), book.getId()),
                () -> assertEquals(testBook.getTitle(), book.getTitle()),
                () -> assertEquals(testBook.getAuthor(), book.getAuthor()),
                () -> assertEquals(testBook.getGenre(), book.getGenre()),
                () -> assertEquals(testBook.getPublicationDate(), book.getPublicationDate()),
                () -> assertEquals(testBook.getIsbn(), book.getIsbn())
        );
    }

}
