package com.postnov.libraryjdbc.servicetest;

import com.postnov.libraryjdbc.dto.AuthorDto;
import com.postnov.libraryjdbc.dto.BookDto;
import com.postnov.libraryjdbc.dto.GenreDto;
import com.postnov.libraryjdbc.model.Author;
import com.postnov.libraryjdbc.model.Book;
import com.postnov.libraryjdbc.model.BookAuthor;
import com.postnov.libraryjdbc.model.Genre;
import com.postnov.libraryjdbc.repository.BookRepository;
import com.postnov.libraryjdbc.service.AuthorService;
import com.postnov.libraryjdbc.service.BookAuthorService;
import com.postnov.libraryjdbc.service.BookService;
import com.postnov.libraryjdbc.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private BookAuthorService bookAuthorService;

    @MockBean
    private GenreService genreService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void saveTest() {
        BookDto bookDto = createBookDto();
        Genre savedGenre = mock(Genre.class);
        Book savedBook = mock(Book.class);
        Author savedAuthor = mock(Author.class);
        BookAuthor savedBookAuthor = mock(BookAuthor.class);

        when(genreService.save(bookDto.getGenre())).thenReturn(savedGenre);
        when(bookRepository.save(new Book(bookDto.getName(), savedGenre.getId()))).thenReturn(savedBook);
        when(authorService.save(bookDto.getAuthors().get(0))).thenReturn(savedAuthor);
        when(bookAuthorService.save(any(BookAuthor.class))).thenReturn(savedBookAuthor);

        bookService.save(bookDto);

        verify(genreService).save(bookDto.getGenre());
        verify(bookRepository).save(new Book(bookDto.getName(), savedGenre.getId()));
        verify(authorService).save(bookDto.getAuthors().get(0));
        verify(bookAuthorService).save(any(BookAuthor.class));
    }

    private BookDto createBookDto() {
        return BookDto.builder()
                .name("bookName")
                .genre(createGenreDto())
                .authors(Collections.singletonList(createAuthorDto()))
                .build();
    }

    private GenreDto createGenreDto() {
        GenreDto genre = new GenreDto();
        genre.setName("genre");
        return genre;
    }

    private AuthorDto createAuthorDto() {
        return AuthorDto.builder()
                .name("authorName")
                .surname("authorSurname")
                .build();
    }
}