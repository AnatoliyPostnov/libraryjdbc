package com.postnov.libraryjdbc.service.impl;

import com.postnov.libraryjdbc.dto.AuthorDto;
import com.postnov.libraryjdbc.model.Author;
import com.postnov.libraryjdbc.repository.AuthorRepository;
import com.postnov.libraryjdbc.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author save(AuthorDto author) {

        Optional<Author> authorInDb = authorRepository.finedAuthorByAuthor(new Author(author.getName(), author.getSurname()));

        if (authorInDb.isEmpty()) {
            return authorRepository.save(new Author(author.getName(), author.getSurname()));
        } else {
            return authorInDb.get();
        }
    }

}
