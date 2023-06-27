package com.example.book.features.mongo.domain;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookDTOTest {

    @NotNull
    public static BookDTO getTestBookDTO() {
        String title = "title";
        String author = "author";
        String genre = "genre";
        Instant date = Instant.now();
        String isbn = "isbn";

        return new BookDTO(title, author, genre, date, isbn);
    }

    @Test
    void shouldValidateBookDTOAndGetter() {
        // given
        BookDTO testBookDTO = getTestBookDTO();

        // when
        BookDTO bookDTO = new BookDTO(testBookDTO.title(), testBookDTO.author(), testBookDTO.genre(), testBookDTO.publicationDate(), testBookDTO.isbn());

        // then
        assertAll(
                "Asserting all bookDTO fields",
                () -> assertEquals(testBookDTO.title(), bookDTO.title()),
                () -> assertEquals(testBookDTO.author(), bookDTO.author()),
                () -> assertEquals(testBookDTO.genre(), bookDTO.genre()),
                () -> assertEquals(testBookDTO.publicationDate(), bookDTO.publicationDate()),
                () -> assertEquals(testBookDTO.isbn(), bookDTO.isbn())
        );
    }
}
