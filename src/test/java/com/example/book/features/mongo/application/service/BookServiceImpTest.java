package com.example.book.features.mongo.application.service;

import com.example.book.features.mongo.application.mapper.BookMapper;
import com.example.book.features.mongo.domain.Book;
import com.example.book.features.mongo.domain.BookDTO;
import com.example.book.features.mongo.domain.BookDTOTest;
import com.example.book.features.mongo.domain.BookTest;
import com.example.book.features.mongo.infrastructure.BookRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceImpTest {

    @InjectMocks
    private BookServiceImp bookServiceImpInjectedMocks;

    @Mock
    private BookRepository bookRepositoryMocked;

    @Mock
    private BookMapper bookMapperMocked;

    @Test
    void shouldImplementBookService() {
        // given
        Class<?>[] interfaces = BookServiceImp.class.getInterfaces();

        // when
        BookServiceImp bookServiceImp = new BookServiceImp(null, null);

        // then
        assertAll(
                "Implements BookService interface",
                () -> assertEquals(1, interfaces.length),
                () -> assertTrue(bookServiceImp instanceof BookService)
        );
    }

    @Nested
    class FindByIdService {

        @Test
        void shouldGetBookDTOFromId() {
            // given
            int id = 1;

            Book book = BookTest.getTestBook();
            when(bookRepositoryMocked.findById(id)).thenReturn(Optional.of(book));

            BookDTO bookDTO = BookDTOTest.getTestBookDTO();
            when(bookMapperMocked.createBookDTO(book)).thenReturn(bookDTO);

            // when
            BookDTO bookDTOResult = bookServiceImpInjectedMocks.findByIdService(id);

            // then
            assertAll(
                    "Getting bookDTO from service",
                    () -> assertEquals(bookDTO, bookDTOResult)
            );

            verify(bookRepositoryMocked).findById(id);
            verify(bookMapperMocked).createBookDTO(book);
        }

        @Test
        void shouldThrowNoSuchElementException() {
            // given
            int id = 1;

            when(bookRepositoryMocked.findById(id)).thenReturn(Optional.empty());

            // when
            Executable executable = () -> bookServiceImpInjectedMocks.findByIdService(id);

            // then
            assertAll(
                    "NoSuchElementException from null received book",
                    () -> assertThrows(NoSuchElementException.class, executable)
            );

            verify(bookRepositoryMocked).findById(id);
        }

        @Test
        void shouldThrowRuntimeException() {
            // given
            int id = 1;

            when(bookRepositoryMocked.findById(id)).thenThrow(RuntimeException.class);

            // when
            Executable executable = () -> bookServiceImpInjectedMocks.findByIdService(id);

            // then
            assertAll(
                    "RuntimeException from DB error",
                    () -> assertThrows(RuntimeException.class, executable)
            );

            verify(bookRepositoryMocked).findById(id);
        }

    }

    @Nested
    class AddNewBook {

        @Test
        void shouldVerifyAddNewBook() {
            // given
            BookDTO bookDTO = new BookDTO(null, null, null, null,null);
            Book book = new Book();

            when(bookMapperMocked.bookDTOToBook(bookDTO)).thenReturn(book);

            // when
            bookServiceImpInjectedMocks.addNewBook(bookDTO);

            // then
            verify(bookMapperMocked).bookDTOToBook(bookDTO);
            verify(bookRepositoryMocked).save(book);
        }

        @Test
        void shouldThrowRuntimeException() {
            // given
            BookDTO bookDTO = BookDTOTest.getTestBookDTO();

            when(bookRepositoryMocked.save(any())).thenThrow(RuntimeException.class);

            // when
            Executable executable = () -> bookServiceImpInjectedMocks.addNewBook(bookDTO);

            // then
            assertAll(
                    "RuntimeException from DB error",
                    () -> assertThrows(RuntimeException.class, executable)
            );

            verify(bookMapperMocked).bookDTOToBook(bookDTO);
            verify(bookRepositoryMocked).save(any());
        }

    }

}
