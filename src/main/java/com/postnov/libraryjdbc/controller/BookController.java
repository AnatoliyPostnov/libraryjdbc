package com.postnov.libraryjdbc.controller;

import com.postnov.libraryjdbc.dto.BookDto;
import com.postnov.libraryjdbc.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/save")
    public void saveBook(@RequestBody BookDto book) {
        bookService.save(book);
    }
}
