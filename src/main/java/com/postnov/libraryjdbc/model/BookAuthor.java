package com.postnov.libraryjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthor {

    Long id;

    Long book_id;

    Long author_id;

}
