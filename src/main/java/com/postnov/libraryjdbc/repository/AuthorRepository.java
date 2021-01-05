package com.postnov.libraryjdbc.repository;

import com.postnov.libraryjdbc.model.Author;

import java.util.Optional;

public interface AuthorRepository {
    
    Author save(Author author);

    Optional<Author> finedAuthorByAuthor(Author author);
    
}
