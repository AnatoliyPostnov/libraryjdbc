package com.postnov.libraryjdbc.repository.impl;

import com.postnov.libraryjdbc.model.Genre;
import com.postnov.libraryjdbc.repository.GenreRepository;
import com.postnov.libraryjdbc.repository.mapper.GenreMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
public class GenreRepositoryImpl implements GenreRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    private final GenreMapper genreMapper;

    public GenreRepositoryImpl(NamedParameterJdbcOperations namedParameterJdbcOperations,
                               GenreMapper genreMapper) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.genreMapper = genreMapper;
    }

    @Override
    public Genre save(Genre genre) {

        if (genre == null) {
            log.error("The genre cannot be saved to the database because he is null");
            throw new NullPointerException();
        }

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", genre.getId())
                .addValue("name", genre.getName());

        namedParameterJdbcOperations.update(
                "insert into genre(id, name) values(:id, :name)",
                params
        );

        return namedParameterJdbcOperations.queryForObject(
                "select * from genre where id = :id",
                params,
                genreMapper
        );
    }

}
