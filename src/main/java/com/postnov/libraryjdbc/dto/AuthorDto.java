package com.postnov.libraryjdbc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorDto {

    private String name;

    private String surname;

    private List<BookDto> books;

}
