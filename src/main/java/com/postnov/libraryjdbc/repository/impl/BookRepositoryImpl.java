package com.postnov.libraryjdbc.repository.impl;

import com.postnov.libraryjdbc.model.Book;
import com.postnov.libraryjdbc.repository.BookRepository;
import com.postnov.libraryjdbc.repository.mapper.BookMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Log4j2
public class BookRepositoryImpl implements BookRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    private final BookMapper bookMapper;

    public BookRepositoryImpl(NamedParameterJdbcOperations namedParameterJdbcOperations,
                              BookMapper bookMapper) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.bookMapper = bookMapper;
    }

    @Override
    public Book save(Book book) {

        checkForNull(book, "The book cannot be saved to the database because he is null");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", book.getName())
                .addValue("genre_id", book.getGenre_id());

        namedParameterJdbcOperations.update(
                "insert into book (name, genre_id) values (:name, :genre_id)",
                params
        );

        return finedBookByBookName(book.getName()).get();
    }

    @Override
    public Optional<Book> finedBookByBookName(String bookName) {

        checkForNull(bookName, "The book cannot be fined in the database because he is null");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", bookName);


        Optional<Book> finedBook = Optional.empty();

        try {
            finedBook = Optional.ofNullable(
                    namedParameterJdbcOperations.queryForObject(
                            "select * from book where name = :name",
                            params,
                            bookMapper
                    )
            );
        } catch (EmptyResultDataAccessException ex) {
            log.info(String.format("book with name = %s was not found", bookName));
        }

        return finedBook;
    }

    private void checkForNull(Object object, String message) {
        if (object == null) {
            log.error(message);
            throw new NullPointerException();
        }
    }
}