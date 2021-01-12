package com.postnov.libraryjdbc.service;

import com.postnov.libraryjdbc.dto.BookDto;

import java.util.Optional;

public interface BookService {

    void save (BookDto bookDto);

    Optional<BookDto> getBookByBookName(String bookName);

    void updateBook(BookDto bookDto);

    void deleteBookByBookName(String bookName);

}