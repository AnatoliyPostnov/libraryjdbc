package com.postnov.libraryjdbc.repository.impl;

import com.postnov.libraryjdbc.model.BookAuthor;
import com.postnov.libraryjdbc.repository.BookAuthorRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BookAuthorRepositoryImpl implements BookAuthorRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookAuthorRepositoryImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public BookAuthor save(BookAuthor bookAuthor) {
        return null;
    }

}
