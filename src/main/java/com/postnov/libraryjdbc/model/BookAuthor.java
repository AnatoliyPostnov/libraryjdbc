package com.postnov.libraryjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class BookAuthor {

    Long id;

    Long book_id;

    Long author_id;

    public BookAuthor(Long book_id, Long author_id) {
        this.book_id = book_id;
        this.author_id = author_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAuthor that = (BookAuthor) o;
        return Objects.equals(book_id, that.book_id) &&
                Objects.equals(author_id, that.author_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id, author_id);
    }
}