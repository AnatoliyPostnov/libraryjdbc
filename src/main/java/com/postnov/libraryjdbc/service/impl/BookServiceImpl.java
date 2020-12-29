package com.postnov.libraryjdbc.service.impl;

import com.postnov.libraryjdbc.dto.BookDto;
import com.postnov.libraryjdbc.model.Book;
import com.postnov.libraryjdbc.model.BookAuthor;
import com.postnov.libraryjdbc.model.Genre;
import com.postnov.libraryjdbc.repository.BookRepository;
import com.postnov.libraryjdbc.service.AuthorService;
import com.postnov.libraryjdbc.service.BookAuthorService;
import com.postnov.libraryjdbc.service.BookService;
import com.postnov.libraryjdbc.service.GenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final BookAuthorService bookAuthorService;

    private final GenreService genreService;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService,
                           BookAuthorService bookAuthorService,
                           GenreService genreService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.bookAuthorService = bookAuthorService;
        this.genreService = genreService;
    }

    @Override
    @Transactional
    public void save(BookDto bookDto) {

        Genre savedGenre = genreService.save(bookDto.getGenre());

        Book savedBook = bookRepository.save(new Book(bookDto.getName(), savedGenre.getId()));

        bookDto.getAuthors()
                .stream()
                .map(authorService::save)
                .forEach(author -> bookAuthorService.save(new BookAuthor(savedBook.getId(), author.getId())));

    }

}
