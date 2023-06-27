package com.example.book.features.mongo.application.mapper;

import com.example.book.features.mongo.domain.Book;
import com.example.book.features.mongo.domain.BookDTOTest;
import com.example.book.features.mongo.domain.BookTest;
import com.example.book.features.mongo.domain.BookDTO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class BookMapperImplTest {

    @Test
    void shouldImplementBookService() {
        // given
        Class<?>[] interfaces = BookMapperImpl.class.getInterfaces();

        // when
        BookMapperImpl bookMapperImpl = new BookMapperImpl();

        // then
        assertAll(
                "Implements BookMapper interface",
                () -> assertEquals(1, interfaces.length),
                () -> assertTrue(bookMapperImpl instanceof BookMapper)
        );
    }

    @Nested
    class CreateBookDTO {

        @Test
        void shouldCreateBookDTOFromBook() {
            // given
            Book book = BookTest.getTestBook();

            BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

            // when
            BookDTO bookDTO = bookMapper.createBookDTO(book);

            // then
            assertAll(
                    "Asserting all fields of the BookDTO after mapping from Book",
                    () -> assertEquals(book.getAuthor(), bookDTO.author()),
                    () -> assertEquals(book.getTitle(), bookDTO.title()),
                    () -> assertEquals(book.getGenre(), bookDTO.genre()),
                    () -> assertEquals(book.getPublicationDate(), bookDTO.publicationDate()),
                    () -> assertEquals(book.getIsbn(), bookDTO.isbn())
            );
        }

        @Test
        void shouldCreateEmptyBookDTOFromEmptyBook() {
            // given
            Book book = new Book();

            BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

            // when
            BookDTO bookDTO = bookMapper.createBookDTO(book);

            // then
            assertAll(
                    "Asserting all fields of the BookDTO are null after mapping from empty Book",
                    () -> assertNull(bookDTO.author()),
                    () -> assertNull(bookDTO.title()),
                    () -> assertNull(bookDTO.genre()),
                    () -> assertNull(bookDTO.publicationDate()),
                    () -> assertNull(bookDTO.isbn())
            );
        }

        @Test
        void shouldGetNullFromCreateBookDTO() {
            // given
            BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

            // when
            BookDTO bookDTO = bookMapper.createBookDTO(null);

            // then
            assertAll(
                    "createBookDTO has to return null BookDTO from a null",
                    () -> assertNull(bookDTO)
            );
        }
    }

    @Nested
    class BookDTOToBook {

        @Test
        void shouldCreateBookDTOFromBook() {
            // given
            BookDTO bookDTO = BookDTOTest.getTestBookDTO();

            BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

            // when
            Book book = bookMapper.bookDTOToBook(bookDTO);

            // then
            assertAll(
                    "Asserting all fields of the BookDTO after mapping from Book",
                    () -> assertEquals(bookDTO.author(), book.getAuthor()),
                    () -> assertEquals(bookDTO.title(), book.getTitle()),
                    () -> assertEquals(bookDTO.genre(), book.getGenre()),
                    () -> assertEquals(bookDTO.publicationDate(), book.getPublicationDate()),
                    () -> assertEquals(bookDTO.isbn(), book.getIsbn())
            );
        }

        @Test
        void shouldCreateEmptyBookDTOFromEmptyBook() {
            // given
            BookDTO bookDTO = new BookDTO(null, null, null, null, null);

            BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

            // when
            Book book = bookMapper.bookDTOToBook(bookDTO);

            // then
            assertAll(
                    "Asserting all fields of the Book are null after mapping from empty BookDTO",
                    () -> assertNull(book.getAuthor()),
                    () -> assertNull(book.getTitle()),
                    () -> assertNull(book.getGenre()),
                    () -> assertNull(book.getPublicationDate()),
                    () -> assertNull(book.getIsbn())
            );
        }

        @Test
        void shouldGetNullFromBookDTOToBook() {
            // given
            BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

            // when
            Book book = bookMapper.bookDTOToBook(null);

            // then
            assertAll(
                    "bookDTOToBook has to return null Book from a null",
                    () -> assertNull(book)
            );
        }

    }

}
