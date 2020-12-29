package com.postnov.libraryjdbc.service;

import com.postnov.libraryjdbc.dto.GenreDto;
import com.postnov.libraryjdbc.model.Genre;

public interface GenreService {

    Genre save(GenreDto genre);

}
