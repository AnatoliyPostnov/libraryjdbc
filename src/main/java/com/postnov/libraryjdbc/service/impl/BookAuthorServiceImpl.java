package com.postnov.libraryjdbc.service.impl;

import com.postnov.libraryjdbc.model.Book;
import com.postnov.libraryjdbc.model.BookAuthor;
import com.postnov.libraryjdbc.repository.BookAuthorRepository;
import com.postnov.libraryjdbc.service.BookAuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {

    private final BookAuthorRepository bookAuthorRepository;

    public BookAuthorServiceImpl(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @Override
    public BookAuthor save(BookAuthor bookAuthor) {

        Optional<BookAuthor> bookAuthorInDb = bookAuthorRepository.finedBookAuthorByBookAuthor(bookAuthor);

        if (bookAuthorInDb.isEmpty()) {
            return bookAuthorRepository.save(bookAuthor);
        } else {
            return bookAuthorInDb.get();
        }
    }

    @Override
    public List<Long> getAuthorsIdByBookId(Long bookId) {
        return bookAuthorRepository.finedAuthorsIdByBookId(bookId);
    }

    @Override
    public void update(Book book, List<Long> newAuthorsId) {
        List<Long> oldAuthorsId = getAuthorsIdByBookId(book.getId());
        List<Long> oldAuthorsIdThatNotInNewAuthorsId = oldAuthorsId
                .stream()
                .filter(id -> !newAuthorsId.contains(id))
                .collect(Collectors.toList());
        oldAuthorsIdThatNotInNewAuthorsId.forEach(
                authorId -> bookAuthorRepository.delete(new BookAuthor(book.getId(), authorId))
        );
        newAuthorsId.forEach(authorId -> save(new BookAuthor(book.getId(), authorId)));
    }
}