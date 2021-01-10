package com.postnov.libraryjdbc.repository.impl;

import com.postnov.libraryjdbc.model.Book;
import com.postnov.libraryjdbc.repository.BookRepository;
import com.postnov.libraryjdbc.repository.mapper.BookMapper;
import lombok.extern.log4j.Log4j2;
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

        if (book == null) {
            log.error("The book cannot be saved to the database because he is null");
            throw new NullPointerException();
        }

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

        if (bookName == null) {
            log.error("The book cannot be fined in the database because he is null");
            throw new NullPointerException();
        }

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", bookName);


        return Optional.ofNullable(
                namedParameterJdbcOperations.queryForObject(
                        "select * from book where name = :name",
                        params,
                        bookMapper
                ));
    }

}
