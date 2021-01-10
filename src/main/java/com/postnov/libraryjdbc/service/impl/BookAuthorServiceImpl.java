package com.postnov.libraryjdbc.service.impl;

import com.postnov.libraryjdbc.model.BookAuthor;
import com.postnov.libraryjdbc.repository.BookAuthorRepository;
import com.postnov.libraryjdbc.service.BookAuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Long> getAuthorsIdByBookId(Long bookId) {
        return bookAuthorRepository.finedAuthorsIdByBookId(bookId);
    }
}
