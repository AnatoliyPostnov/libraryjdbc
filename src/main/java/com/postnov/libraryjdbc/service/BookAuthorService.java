package com.postnov.libraryjdbc.service;

import com.postnov.libraryjdbc.model.BookAuthor;

import java.util.List;

public interface BookAuthorService {

    BookAuthor save(BookAuthor bookAuthor);

    List<Long> getAuthorsIdByBookId(Long bookId);

}