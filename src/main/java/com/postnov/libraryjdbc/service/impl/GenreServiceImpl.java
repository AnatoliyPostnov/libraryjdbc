package com.postnov.libraryjdbc.service.impl;

import com.postnov.libraryjdbc.dto.GenreDto;
import com.postnov.libraryjdbc.exception.NotFoundException;
import com.postnov.libraryjdbc.model.Genre;
import com.postnov.libraryjdbc.repository.GenreRepository;
import com.postnov.libraryjdbc.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre save(GenreDto genre) {

        Optional<Genre> genreInDb = genreRepository.finedGenreByGenre(new Genre(genre.getName()));

        if (genreInDb.isEmpty()){
            return genreRepository.save(new Genre(genre.getName()));
        } else {
            return genreInDb.get();
        }
    }

    @Override
    public GenreDto getGenreById(Long id) {
        Genre finedGenre = genreRepository
                .finedGenreById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Genre with book id = %s", id)));
        GenreDto finedGenreDto =  new GenreDto();
        finedGenreDto.setName(finedGenre.getName());
        return finedGenreDto;
    }
}