package com.postnov.libraryjdbc.repositorytest;

import com.postnov.libraryjdbc.model.Genre;
import com.postnov.libraryjdbc.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(value = {"/drop_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/drop_data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    private Genre genre;

    @BeforeEach
    void setUp() {
        genre = new Genre((long) 1, "genre_name");
    }

    @Test
    void saveTest() {
        Genre savedGenre = genreRepository.save(genre);
        assertEquals(genre, savedGenre);
    }

    @Test
    void finedGenreByGenreTest() {
        genreRepository.save(genre);
        Optional<Genre> finedGenre = genreRepository.finedGenreByGenre(genre);
        assertEquals(genre, finedGenre.get());
    }

    @Test
    void finedGenreByGenreIfGenreIsNotInDbTest() {
        Optional<Genre> finedGenre = genreRepository.finedGenreByGenre(genre);
        assertEquals(Optional.empty(), finedGenre);
    }

    @Test
    void finedGenreByIdTest() {
        Genre savedGenre = genreRepository.save(genre);
        Optional<Genre> finedGenre = genreRepository.finedGenreById(savedGenre.getId());
        assertEquals(genre, finedGenre.get());
    }

    @Test
    void finedGenreByIdIfGenreIsNotInDbTest() {
        Optional<Genre> finedGenre = genreRepository.finedGenreById(genre.getId());
        assertEquals(Optional.empty(), finedGenre);
    }
}
