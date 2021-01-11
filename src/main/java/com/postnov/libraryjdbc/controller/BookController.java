package com.postnov.libraryjdbc.controller;

import com.postnov.libraryjdbc.dto.BookDto;
import com.postnov.libraryjdbc.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public ResponseEntity<BookDto> getByBookName(@RequestParam(value = "bookName") String bookName) {
        System.out.println(bookName);
        return ResponseEntity.of(bookService.getBookByBookName(bookName));
    }
}