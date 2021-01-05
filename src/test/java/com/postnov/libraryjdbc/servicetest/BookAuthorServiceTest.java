package com.postnov.libraryjdbc.servicetest;

import com.postnov.libraryjdbc.model.BookAuthor;
import com.postnov.libraryjdbc.repository.BookAuthorRepository;
import com.postnov.libraryjdbc.service.BookAuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BookAuthorServiceTest {

    @Autowired
    BookAuthorService bookAuthorService;

    @MockBean
    BookAuthorRepository bookAuthorRepository;

    @Test
    void saveTest() {
        BookAuthor bookAuthor = mock(BookAuthor.class);

        when(bookAuthorRepository.save(bookAuthor)).thenReturn(bookAuthor);

        bookAuthorService.save(bookAuthor);

        verify(bookAuthorRepository).save(bookAuthor);
    }
}
