package com.postnov.libraryjdbc.repositorytest;

import com.postnov.libraryjdbc.model.Genre;
import com.postnov.libraryjdbc.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Sql(value = {"/drop_data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void saveTest() {
        Genre genre = new Genre((long) 1, "genre_name");
        Genre savedGenre = genreRepository.save(genre);
        assertEquals(genre, savedGenre);
    }

}
