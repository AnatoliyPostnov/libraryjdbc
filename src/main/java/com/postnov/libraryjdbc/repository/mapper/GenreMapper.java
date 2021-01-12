package com.postnov.libraryjdbc.repository.mapper;

import com.postnov.libraryjdbc.model.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GenreMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Genre(
                rs.getLong("id"),
                rs.getString("name")
        );
    }

}
