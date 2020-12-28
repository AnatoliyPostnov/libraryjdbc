package com.postnov.libraryjdbc.repository;

import com.postnov.libraryjdbc.model.Author;

public interface AuthorRepository {
    
    Author save(Author author);
    
}
