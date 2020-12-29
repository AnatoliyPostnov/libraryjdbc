package com.postnov.libraryjdbc.repository.impl;

import com.postnov.libraryjdbc.model.BookAuthor;
import com.postnov.libraryjdbc.repository.BookAuthorRepository;
import com.postnov.libraryjdbc.repository.mapper.BookAuthorMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
public class BookAuthorRepositoryImpl implements BookAuthorRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    private final BookAuthorMapper bookAuthorMapper;

    public BookAuthorRepositoryImpl(NamedParameterJdbcOperations namedParameterJdbcOperations,
                                    BookAuthorMapper bookAuthorMapper) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.bookAuthorMapper = bookAuthorMapper;
    }

    @Override
    public BookAuthor save(BookAuthor bookAuthor) {

        if (bookAuthor == null) {
            log.error("The bookAuthor cannot be saved to the database because he is null");
            throw new NullPointerException();
        }

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("book_id", bookAuthor.getBook_id())
                .addValue("author_id", bookAuthor.getAuthor_id());

        namedParameterJdbcOperations.update(
                "insert into book_author(book_id, author_id) values(:book_id, :author_id)",
                params
        );

        return namedParameterJdbcOperations.queryForObject(
                "select * from book_author where book_id = :book_id and author_id = :author_id",
                params,
                bookAuthorMapper
        );
    }

}
