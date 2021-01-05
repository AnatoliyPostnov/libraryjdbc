package com.postnov.libraryjdbc.repository;

import com.postnov.libraryjdbc.model.Genre;

import java.util.Optional;

public interface GenreRepository {

    Genre save(Genre genre);

    Optional<Genre> finedGenreByGenre(Genre genre);

}
