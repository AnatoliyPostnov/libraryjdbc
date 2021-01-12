package com.postnov.libraryjdbc.repository.impl;

import com.postnov.libraryjdbc.model.BookAuthor;
import com.postnov.libraryjdbc.repository.BookAuthorRepository;
import com.postnov.libraryjdbc.repository.mapper.BookAuthorMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        checkForNull(bookAuthor, "The bookAuthor cannot be saved to the database because he is null");

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

    @Override
    public Optional<BookAuthor> finedBookAuthorByBookAuthor(BookAuthor bookAuthor) {

        checkForNull(bookAuthor, "The bookAuthor cannot be fined in the database because input bookAuthor is null");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("book_id", bookAuthor.getBook_id())
                .addValue("author_id", bookAuthor.getAuthor_id());

        Optional<BookAuthor> finedBookAuthor = Optional.empty();

        try {
            finedBookAuthor = Optional.ofNullable(
                    namedParameterJdbcOperations.queryForObject(
                            "select * from book_author where book_id = :book_id and author_id = :author_id",
                            params,
                            bookAuthorMapper
                    )
            );
        } catch (EmptyResultDataAccessException ex) {
            log.info(String.format("bookAuthor with bookId = %s and authorId = %s was not found", bookAuthor.getBook_id(), bookAuthor.getAuthor_id()));
        }

        return finedBookAuthor;
    }

    @Override
    public List<BookAuthor> finedBookAuthorsByBookId(Long bookId) {
        checkForNull(bookId, "The list authorsId cannot be fined in the database because input bookId is null");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("book_id", bookId);

        return new ArrayList<>(namedParameterJdbcOperations.query(
                "select * from book_author where book_id = :book_id",
                params,
                bookAuthorMapper
        ));
    }

    @Override
    public BookAuthor delete(BookAuthor bookAuthor) {

        checkForNull(bookAuthor, "The bookAuthor cannot be fined in the database because input bookAuthor is null");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("book_id", bookAuthor.getBook_id())
                .addValue("author_id", bookAuthor.getAuthor_id());

        Optional<BookAuthor> deletedBookAuthor = finedBookAuthorByBookAuthor(bookAuthor);

        namedParameterJdbcOperations.update(
                "delete from book_author where book_id = :book_id and author_id = :author_id",
                params
        );

        return deletedBookAuthor.get();
    }

    private void checkForNull(Object object, String message) {
        if (object == null) {
            log.error(message);
            throw new NullPointerException();
        }
    }
}