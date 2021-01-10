package com.postnov.libraryjdbc.repository.impl;

import com.postnov.libraryjdbc.model.Author;
import com.postnov.libraryjdbc.repository.AuthorRepository;
import com.postnov.libraryjdbc.repository.mapper.AuthorMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
                .addValue("name", author.getName())
                .addValue("surname", author.getSurname());

        namedParameterJdbcOperations.update(
                "insert into author (name, surname) values (:name, :surname)",
                params
        );

        return finedAuthorByAuthor(author).get();
    }

    @Override
    public Optional<Author> finedAuthorByAuthor(Author author) {

        if (author == null) {
            log.error("The author cannot be fined in the database because he is null");
            throw new NullPointerException();
        }

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", author.getName())
                .addValue("surname", author.getSurname());

        Optional<Author> finedAuthor = Optional.empty();

        try {
            finedAuthor = Optional.ofNullable(
                    namedParameterJdbcOperations.queryForObject(
                            "select * from author where name = :name and surname = :surname",
                            params,
                            authorMapper
                    )
            );
        } catch (EmptyResultDataAccessException ex) {
            log.info(String.format("author with name = %s and surname = %s was not found", author.getName(), author.getSurname()));
        }

        return finedAuthor;

    }

    @Override
    public Optional<Author> finedAuthorByAuthorId(Long authorId) {

        if (authorId == null) {
            log.error("The author cannot be fined in the database because input authorId is null");
            throw new NullPointerException();
        }

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", authorId);

        return Optional.ofNullable(
                namedParameterJdbcOperations.queryForObject(
                        "select * from author where id = :id",
                        params,
                        authorMapper
                ));
    }

}
