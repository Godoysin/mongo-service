package com.example.book.features.mongo.infrastructure.controller;

import com.example.book.features.mongo.application.service.BookService;
import com.example.book.features.mongo.domain.BookDTO;
import com.example.book.features.mongo.domain.BookDTOTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MongoCRUDTest {

    @InjectMocks
    private MongoCRUD mongoCRUDInjectedMocks;

    @Mock
    private BookService bookServiceMocked;

    @Nested
    class MethodTests {

        @Test
        void shouldBookByIdInterface() {
            // Given
            int bookId = 1;
            BookDTO expectedDTO = new BookDTO(null, null, null, null, null);
            when(bookServiceMocked.findByIdService(bookId)).thenReturn(expectedDTO);

            // When
            BookDTO actualDTO = mongoCRUDInjectedMocks.bookByIdInterface(bookId);

            // Then
            assertAll(
                    "asd",
                    () -> assertNotNull(actualDTO),
                    () -> assertEquals(expectedDTO, actualDTO)
            );

            verify(bookServiceMocked).findByIdService(bookId);
        }

        @Test
        void shouldAddNewBook() {
            // Given
            BookDTO bookDTO = new BookDTO(null, null, null, null, null);

            // When
            mongoCRUDInjectedMocks.addNewBook(bookDTO);

            // Then
            verify(bookServiceMocked).addNewBook(bookDTO);
        }
    }

    @Nested
    @SpringBootTest
    @AutoConfigureMockMvc
    class HTTPTests {
        @Autowired
        private MockMvc mockMvc;

        @Test
        void shouldBookByIdInterfaceGETCall() throws Exception {
            // Given
            int bookId = 1;
            BookDTO expectedDTO = BookDTOTest.getTestBookDTO();

            // When & Then
            mockMvc.perform(get("/books/book-by-id")
                            .param("id", String.valueOf(bookId)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("title").exists())
                    .andExpect(jsonPath("title").value(expectedDTO.title()))
                    .andExpect(jsonPath("author").exists())
                    .andExpect(jsonPath("author").value(expectedDTO.author()))
                    .andExpect(jsonPath("genre").exists())
                    .andExpect(jsonPath("genre").value(expectedDTO.genre()))
                    .andExpect(jsonPath("publicationDate").exists())
                    .andExpect(jsonPath("isbn").exists())
                    .andExpect(jsonPath("isbn").value(expectedDTO.isbn()));
        }

        @Test
        void shouldAddNewBookWithPOSTCall() throws Exception {
            // Given
            BookDTO bookDTO = BookDTOTest.getTestBookDTO();

            String bookDTOAsJson = """
                    {
                        "title": "%s",
                        "author": "%s",
                        "genre": "%s",
                        "publicationDate": "%s",
                        "isbn": "%s"
                    }
                    """.formatted(bookDTO.title(), bookDTO.author(), bookDTO.genre(), bookDTO.publicationDate(), bookDTO.isbn());

            // When & Then
            mockMvc.perform(post("/books/book")
                            .content(bookDTOAsJson)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

    }

}
