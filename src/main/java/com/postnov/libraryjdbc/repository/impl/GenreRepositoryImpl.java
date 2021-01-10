package com.postnov.libraryjdbc.repository.impl;

import com.postnov.libraryjdbc.model.Genre;
import com.postnov.libraryjdbc.repository.GenreRepository;
import com.postnov.libraryjdbc.repository.mapper.GenreMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
                .addValue("name", genre.getName());

        namedParameterJdbcOperations.update(
                "insert into genre(name) values(:name)",
                params
        );

        return finedGenreByGenre(genre).get();
    }

    @Override
    public Optional<Genre> finedGenreByGenre(Genre genre) {

        if (genre == null) {
            log.error("The genre cannot be fined in the database because he is null");
            throw new NullPointerException();
        }

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", genre.getName());

        Optional<Genre> finedGenre = Optional.empty();

        try {
            finedGenre = Optional.ofNullable(
                    namedParameterJdbcOperations.queryForObject(
                            "select * from genre where name = :name",
                            params,
                            genreMapper
                    )
            );
        } catch (EmptyResultDataAccessException ex) {
            log.info(String.format("genre with name = %s was not found", genre.getName()));
        }

        return finedGenre;
    }

    @Override
    public Optional<Genre> finedGenreById(Long id) {

        if (id == null) {
            log.error("The genre cannot be fined in the database because input id is null");
            throw new NullPointerException();
        }

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        return Optional.ofNullable(
                namedParameterJdbcOperations.queryForObject(
                        "select * from genre where id = :id",
                        params,
                        genreMapper
                )
        );
    }

}
