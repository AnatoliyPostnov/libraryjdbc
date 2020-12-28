package com.postnov.libraryjdbc.repository;

import com.postnov.libraryjdbc.model.Book;

public interface BookRepository {

    Book save(Book book);

}
