package com.postnov.libraryjdbc.servicetest;

import com.postnov.libraryjdbc.model.Book;
import com.postnov.libraryjdbc.model.BookAuthor;
import com.postnov.libraryjdbc.repository.BookAuthorRepository;
import com.postnov.libraryjdbc.service.BookAuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BookAuthorServiceTest {

    @Autowired
    BookAuthorService bookAuthorService;

    @MockBean
    BookAuthorRepository bookAuthorRepository;

    @Test
    void saveIfAuthorNotExistInDbTest() {
        BookAuthor savedBookAuthor = mock(BookAuthor.class);
        when(bookAuthorRepository.finedBookAuthorByBookAuthor(new BookAuthor((long) 1, (long) 1))).thenReturn(Optional.empty());
        when(bookAuthorRepository.save(new BookAuthor((long) 1, (long) 1))).thenReturn(savedBookAuthor);

        bookAuthorService.save(new BookAuthor((long) 1, (long) 1));

        verify(bookAuthorRepository).finedBookAuthorByBookAuthor(new BookAuthor((long) 1, (long) 1));
        verify(bookAuthorRepository).save(new BookAuthor((long) 1, (long) 1));
    }

    @Test
    void saveIfAuthorExistInDbTest() {
        BookAuthor savedBookAuthor = mock(BookAuthor.class);
        when(bookAuthorRepository.finedBookAuthorByBookAuthor(new BookAuthor((long) 1, (long) 1))).thenReturn(Optional.ofNullable(savedBookAuthor));
        when(bookAuthorRepository.save(new BookAuthor((long) 1, (long) 1))).thenReturn(savedBookAuthor);

        bookAuthorService.save(new BookAuthor((long) 1, (long) 1));

        verify(bookAuthorRepository).finedBookAuthorByBookAuthor(new BookAuthor((long) 1, (long) 1));
        verify(bookAuthorRepository, never()).save(new BookAuthor((long) 1, (long) 1));
    }

    @Test
    void getAuthorsIdByBookId() {
        when(bookAuthorRepository.finedAuthorsIdByBookId((long) 1)).thenReturn(Collections.singletonList((long) 1));

        bookAuthorService.getAuthorsIdByBookId((long) 1);

        verify(bookAuthorRepository).finedAuthorsIdByBookId((long) 1);
    }

    @Test
    void updateNeverCallOldAuthorsTest() {
        Book updatedBook = mock(Book.class);
        List<Long> newAuthorsId = Collections.singletonList((long) 1);

        when(updatedBook.getId()).thenReturn((long) 1);
        when(bookAuthorRepository.finedAuthorsIdByBookId((long) 1)).thenReturn(Collections.singletonList((long) 1));

        bookAuthorService.update(updatedBook, newAuthorsId);

        verify(bookAuthorRepository).finedAuthorsIdByBookId((long) 1);
        verify(bookAuthorRepository, never()).delete(new BookAuthor((long) 1, (long) 1));
    }

    @Test
    void updateCallOldAuthorsTest() {
        Book updatedBook = mock(Book.class);
        List<Long> newAuthorsId = Collections.singletonList((long) 1);

        when(updatedBook.getId()).thenReturn((long) 2);
        when(bookAuthorRepository.finedAuthorsIdByBookId((long) 2)).thenReturn(Collections.singletonList((long) 2));

        bookAuthorService.update(updatedBook, newAuthorsId);

        verify(bookAuthorRepository).finedAuthorsIdByBookId((long) 2);
        verify(bookAuthorRepository).delete(new BookAuthor((long) 2, (long) 2));
    }
}