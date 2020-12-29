package com.postnov.libraryjdbc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookDto {

    private String name;

    private GenreDto genre;

    private List<AuthorDto> authors;

}
