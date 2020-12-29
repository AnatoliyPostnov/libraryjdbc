package com.postnov.libraryjdbc.service.impl;

import com.postnov.libraryjdbc.model.BookAuthor;
import com.postnov.libraryjdbc.repository.BookAuthorRepository;
import com.postnov.libraryjdbc.service.BookAuthorService;
import org.springframework.stereotype.Service;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {

    private final BookAuthorRepository bookAuthorRepository;

    public BookAuthorServiceImpl(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @Override
    public BookAuthor save(BookAuthor bookAuthor) {
        return bookAuthorRepository.save(bookAuthor);
    }
}
