package com.postnov.libraryjdbc.repository.mapper;

import com.postnov.libraryjdbc.model.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int i) throws SQLException {
        return new Author(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("surname")
        );
    }

}
