package com.postnov.libraryjdbc.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Book {

    Long id;

    String name;

    Long genre_id;

}
