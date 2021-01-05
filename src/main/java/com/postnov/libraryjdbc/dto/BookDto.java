package com.postnov.libraryjdbc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
public class BookDto implements Serializable {

    private String name;

    private GenreDto genre;

    private List<AuthorDto> authors;

}
