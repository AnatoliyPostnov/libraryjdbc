package com.postnov.libraryjdbc.repository;

import com.postnov.libraryjdbc.model.Book;

import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> finedBookByBookName(String bookName);

    Book update(Book book);

}