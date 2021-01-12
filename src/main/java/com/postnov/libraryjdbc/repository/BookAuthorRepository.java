package com.postnov.libraryjdbc.repository;

import com.postnov.libraryjdbc.model.BookAuthor;

import java.util.List;
import java.util.Optional;

public interface BookAuthorRepository {

    BookAuthor save(BookAuthor bookAuthor);

    List<Long> finedAuthorsIdByBookId(Long bookId);

    Optional<BookAuthor> finedBookAuthorByBookAuthor(BookAuthor bookAuthor);

    BookAuthor delete(BookAuthor bookAuthor);

}