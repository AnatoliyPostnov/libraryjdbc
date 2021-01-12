package com.postnov.libraryjdbc.repository.mapper;

import com.postnov.libraryjdbc.model.BookAuthor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookAuthorMapper implements RowMapper<BookAuthor> {

    @Override
    public BookAuthor mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BookAuthor(
                rs.getLong("id"),
                rs.getLong("book_id"),
                rs.getLong("author_id")
        );
    }

}
