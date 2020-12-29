package com.postnov.libraryjdbc.service.impl;

import com.postnov.libraryjdbc.dto.AuthorDto;
import com.postnov.libraryjdbc.model.Author;
import com.postnov.libraryjdbc.repository.AuthorRepository;
import com.postnov.libraryjdbc.service.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author save(AuthorDto author) {
        return authorRepository.save(new Author(author.getName(), author.getSurname()));
    }

}
