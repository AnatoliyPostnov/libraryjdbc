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

    @BeforeEach
    void setUp() {

    }

    @Test
    void saveTest() {
        Genre genre = new Genre((long) 1, "genre_name");
        Author author = new Author((long) 1, "author_name", "author_surname");
        Genre savedGenre = genreRepository.save(genre);
        Book book = new Book((long) 1, "book_name", savedGenre.getId());
        Book savedBook = bookRepository.save(book);
        Author savedAuthor = authorRepository.save(author);
        BookAuthor bookAuthor = new BookAuthor((long) 1, savedBook.getId(), savedAuthor.getId());
        BookAuthor savedBookAuthor = bookAuthorRepository.save(bookAuthor);
        assertEquals(bookAuthor, savedBookAuthor);
    }

}
