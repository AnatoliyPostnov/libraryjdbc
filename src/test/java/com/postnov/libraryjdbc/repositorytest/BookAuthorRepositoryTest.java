package com.postnov.libraryjdbc.repositorytest;

import com.postnov.libraryjdbc.model.Author;
import com.postnov.libraryjdbc.model.Book;
import com.postnov.libraryjdbc.model.BookAuthor;
import com.postnov.libraryjdbc.model.Genre;
import com.postnov.libraryjdbc.repository.AuthorRepository;
import com.postnov.libraryjdbc.repository.BookAuthorRepository;
import com.postnov.libraryjdbc.repository.BookRepository;
import com.postnov.libraryjdbc.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(value = {"/drop_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/drop_data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BookAuthorRepositoryTest {

    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private BookAuthor bookAuthor;

    @BeforeEach
    void setUp() {
        Genre genre = new Genre((long) 1, "genre_name");
        Author author = new Author((long) 1, "author_name", "author_surname");
        Genre savedGenre = genreRepository.save(genre);
        Book book = new Book((long) 1, "book_name", savedGenre.getId());
        Book savedBook = bookRepository.save(book);
        Author savedAuthor = authorRepository.save(author);
        bookAuthor = new BookAuthor((long) 1, savedBook.getId(), savedAuthor.getId());
    }

    @Test
    void saveTest() {
        BookAuthor savedBookAuthor = bookAuthorRepository.save(bookAuthor);
        assertEquals(bookAuthor, savedBookAuthor);
    }

    @Test
    void finedBookAuthorsByBookIdTest() {
        bookAuthorRepository.save(bookAuthor);
        List<BookAuthor> authorsId = bookAuthorRepository.finedBookAuthorsByBookId(bookAuthor.getBook_id());
        assertEquals(authorsId.size(), 1);
    }

    @Test
    void finedBookAuthorByBookAuthorTest() {
        BookAuthor savedBookAuthor = bookAuthorRepository.save(bookAuthor);
        Optional<BookAuthor> finedBookAuthor = bookAuthorRepository.finedBookAuthorByBookAuthor(bookAuthor);
        assertEquals(savedBookAuthor.getAuthor_id(), finedBookAuthor.get().getAuthor_id());
        assertEquals(savedBookAuthor.getBook_id(), finedBookAuthor.get().getBook_id());
    }

    @Test
    void deleteTest() {
        BookAuthor savedBookAuthor = bookAuthorRepository.save(bookAuthor);
        BookAuthor deletedBookAuthor = bookAuthorRepository.delete(bookAuthor);

        assertEquals(savedBookAuthor.getBook_id(), deletedBookAuthor.getBook_id());
        assertEquals(savedBookAuthor.getAuthor_id(), deletedBookAuthor.getAuthor_id());
        assertEquals(Optional.empty(), bookAuthorRepository.finedBookAuthorByBookAuthor(bookAuthor));
    }
}
