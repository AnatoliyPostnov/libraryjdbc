package com.postnov.libraryjdbc.repository.mapper;

import com.postnov.libraryjdbc.model.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("genre_id"));
    }

}
