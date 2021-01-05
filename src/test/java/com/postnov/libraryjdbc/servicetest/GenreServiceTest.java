package com.postnov.libraryjdbc.servicetest;

import com.postnov.libraryjdbc.dto.GenreDto;
import com.postnov.libraryjdbc.model.Genre;
import com.postnov.libraryjdbc.repository.GenreRepository;
import com.postnov.libraryjdbc.service.GenreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    @MockBean
    private GenreRepository genreRepository;

    @Test
    void saveTest() {
        GenreDto genre = createGenreDto();
        Genre savedGenre = mock(Genre.class);
        when(genreRepository.save(new Genre(genre.getName()))).thenReturn(savedGenre);

        genreService.save(genre);

        verify(genreRepository).save(new Genre(genre.getName()));
    }

    private GenreDto createGenreDto() {
        GenreDto genre = new GenreDto();
        genre.setName("genre");
        return genre;
    }
}
