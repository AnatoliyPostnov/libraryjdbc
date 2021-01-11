package com.postnov.libraryjdbc.servicetest;

import com.postnov.libraryjdbc.dto.AuthorDto;
import com.postnov.libraryjdbc.model.Author;
import com.postnov.libraryjdbc.repository.AuthorRepository;
import com.postnov.libraryjdbc.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Autowired
    AuthorService authorService;

    @MockBean
    private AuthorRepository authorRepository;

    private AuthorDto author;

    private Author savedAuthor;

    @BeforeEach
    void setUp() {
        author = createAuthorDto();
        savedAuthor = mock(Author.class);
    }

    @Test
    void saveIfAuthorNotExistInDbTest() {
        when(authorRepository.finedAuthorByAuthor(new Author(author.getName(), author.getSurname()))).thenReturn(Optional.empty());
        when(authorRepository.save(new Author(author.getName(), author.getSurname()))).thenReturn(savedAuthor);

        authorService.save(author);

        verify(authorRepository).finedAuthorByAuthor(new Author(author.getName(), author.getSurname()));
        verify(authorRepository).save(new Author(author.getName(), author.getSurname()));
    }

    @Test
    void saveIfAuthorExistInDbTest() {
        when(authorRepository.finedAuthorByAuthor(new Author(author.getName(), author.getSurname()))).thenReturn(Optional.ofNullable(savedAuthor));
        when(authorRepository.save(new Author(author.getName(), author.getSurname()))).thenReturn(savedAuthor);

        authorService.save(author);

        verify(authorRepository).finedAuthorByAuthor(new Author(author.getName(), author.getSurname()));
        verify(authorRepository, never()).save(new Author(author.getName(), author.getSurname()));
    }

    @Test
    void getAuthorByIdTest() {
        when(authorRepository.finedAuthorByAuthorId((long) 1)).thenReturn(Optional.of(new Author("authorName", "authorSurname")));

        AuthorDto finedAuthor = authorService.getAuthorById((long) 1);

        verify(authorRepository).finedAuthorByAuthorId((long) 1);
        assertEquals(finedAuthor.getName(), author.getName());
        assertEquals(finedAuthor.getSurname(), author.getSurname());
    }

    private AuthorDto createAuthorDto() {
        return AuthorDto.builder()
                .name("authorName")
                .surname("authorSurname")
                .build();
    }
}
