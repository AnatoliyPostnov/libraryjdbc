package com.postnov.libraryjdbc.servicetest;

import com.postnov.libraryjdbc.dto.AuthorDto;
import com.postnov.libraryjdbc.dto.BookDto;
import com.postnov.libraryjdbc.dto.GenreDto;
import com.postnov.libraryjdbc.exception.NotFoundException;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void getBookByBookNameTest() {
        GenreDto genre = mock(GenreDto.class);
        AuthorDto author = mock(AuthorDto.class);
        Book finedBook = new Book("bookName", (long) 1);
        finedBook.setId((long) 1);

        when(bookRepository.finedBookByBookName("bookName")).thenReturn(Optional.of(finedBook));
        when(genreService.getGenreById((long) 1)).thenReturn(genre);
        when(bookAuthorService.getAuthorsIdByBookId((long) 1)).thenReturn(Collections.singletonList((long) 1));
        when(authorService.getAuthorById((long) 1)).thenReturn(author);

        Optional<BookDto> resultBookDto = bookService.getBookByBookName("bookName");

        verify(bookRepository).finedBookByBookName("bookName");
        verify(genreService).getGenreById((long) 1);
        verify(bookAuthorService).getAuthorsIdByBookId((long) 1);
        verify(authorService).getAuthorById((long) 1);

        BookDto finedBookDto = BookDto.builder()
                .name("bookName")
                .authors(Collections.singletonList(author))
                .genre(genre)
                .build();

        assertEquals(resultBookDto.get().getName(), finedBookDto.getName());
        assertEquals(resultBookDto.get().getAuthors(), finedBookDto.getAuthors());
        assertEquals(resultBookDto.get().getGenre(), finedBookDto.getGenre());
    }

    @Test
    void updateTest() {
        BookDto bookDto = createBookDto();

        when(bookRepository.finedBookByBookName("bookName")).thenReturn(Optional.of(new Book((long) 1, "bookName", (long) 1)));
        when(genreService.save(bookDto.getGenre())).thenReturn(new Genre((long) 1, "genre"));
        when(bookRepository.update(new Book("bookName", (long) 1))).thenReturn(new Book((long) 1, "bookName", (long) 1));
        when(authorService.save(bookDto.getAuthors().get(0))).thenReturn(new Author((long) 1, "authorName", "authorSurname"));

        bookService.updateBook(bookDto);

        verify(bookRepository).finedBookByBookName("bookName");
        verify(genreService).save(bookDto.getGenre());
        verify(bookRepository).update(new Book("bookName", (long) 1));
        verify(authorService).save(bookDto.getAuthors().get(0));
        verify(bookAuthorService).update(new Book((long) 1, "bookName", (long) 1), Collections.singletonList((long) 1));
    }

    @Test
    void deleteBookByBookNameIfBookIsInDbTest() {
        when(bookRepository.finedBookByBookName("bookName")).thenReturn(Optional.of(new Book((long) 1, "bookName", (long) 1)));

        bookService.deleteBookByBookName("bookName");

        verify(bookAuthorService).deleteBookAuthorsByBookId((long) 1);
        verify(bookRepository).delete(new Book((long) 1, "bookName", (long) 1));
    }

    @Test
    void deleteBookByBookNameIfBookIsNotInDbTest() {
        when(bookRepository.finedBookByBookName("bookName")).thenReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> bookService.deleteBookByBookName("bookName"));
        assertThat(thrown).isInstanceOf(NotFoundException.class);

        verify(bookAuthorService, never()).deleteBookAuthorsByBookId((long) 1);
        verify(bookRepository, never()).delete(new Book((long) 1, "bookName", (long) 1));
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