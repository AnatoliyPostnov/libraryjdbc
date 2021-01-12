package com.postnov.libraryjdbc.repository;

import com.postnov.libraryjdbc.model.BookAuthor;

import java.util.List;
import java.util.Optional;

public interface BookAuthorRepository {

    BookAuthor save(BookAuthor bookAuthor);

    Optional<BookAuthor> finedBookAuthorByBookAuthor(BookAuthor bookAuthor);

    List<BookAuthor> finedBookAuthorsByBookId(Long bookId);

    BookAuthor delete(BookAuthor bookAuthor);
}