package com.postnov.libraryjdbc.repository.impl;

import com.postnov.libraryjdbc.model.Author;
import com.postnov.libraryjdbc.repository.AuthorRepository;
import com.postnov.libraryjdbc.repository.mapper.AuthorMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
public class AuthorRepositoryImpl implements AuthorRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    private final AuthorMapper authorMapper;

    public AuthorRepositoryImpl(NamedParameterJdbcOperations namedParameterJdbcOperations,
                                AuthorMapper authorMapper) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorMapper = authorMapper;
    }

    @Override
    public Author save(Author author) {

        if (author == null) {
            log.error("The author cannot be saved to the database because he is null");
            throw new NullPointerException();
        }

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", author.getId())
                .addValue("name", author.getName())
                .addValue("surname", author.getSurname());

        namedParameterJdbcOperations.update(
                "insert into author (id, name, surname) values (:id, :name, :surname)",
                params
        );

        return namedParameterJdbcOperations.queryForObject(
                "select * from author where id = :id",
                params,
                authorMapper
        );
    }

}
