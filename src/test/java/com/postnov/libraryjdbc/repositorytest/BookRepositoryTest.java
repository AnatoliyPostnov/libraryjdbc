package com.postnov.libraryjdbc.repositorytest;

import com.postnov.libraryjdbc.model.Book;
import com.postnov.libraryjdbc.model.Genre;
import com.postnov.libraryjdbc.repository.BookRepository;
import com.postnov.libraryjdbc.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(value = {"/drop_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/drop_data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    private Book book;

    @BeforeEach
    void setUp() {
        Genre genre = new Genre((long) 1, "genre_name");
        Genre savedGenre = genreRepository.save(genre);
        book = new Book((long) 1, "book_name", savedGenre.getId());
    }

    @Test
    void saveTest() {
        Book savedBook = bookRepository.save(book);
        assertEquals(book, savedBook);
    }

    @Test
    void finedBookByBookNameTest(){
        bookRepository.save(book);
        Optional<Book> finedBook = bookRepository.finedBookByBookName(book.getName());
        assertEquals(book, finedBook.get());
    }

    @Test
    void finedBookByBookNameIfBookIsNotInDbTest(){
        Optional<Book> finedBook = bookRepository.finedBookByBookName(book.getName());
        assertEquals(Optional.empty(), finedBook);
    }
}
