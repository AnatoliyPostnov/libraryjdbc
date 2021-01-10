package com.postnov.libraryjdbc.repository;

import com.postnov.libraryjdbc.model.BookAuthor;

import java.util.List;

public interface BookAuthorRepository {

    BookAuthor save(BookAuthor bookAuthor);

    List<Long> finedAuthorsIdByBookId(Long bookId);

}
