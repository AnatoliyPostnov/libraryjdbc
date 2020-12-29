package com.postnov.libraryjdbc.service.impl;

import com.postnov.libraryjdbc.dto.GenreDto;
import com.postnov.libraryjdbc.model.Genre;
import com.postnov.libraryjdbc.repository.GenreRepository;
import com.postnov.libraryjdbc.service.GenreService;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre save(GenreDto genre) {
        return genreRepository.save(new Genre(genre.getName()));
    }
}
