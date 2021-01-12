package com.postnov.libraryjdbc.service;

import com.postnov.libraryjdbc.dto.AuthorDto;
import com.postnov.libraryjdbc.model.Author;

public interface AuthorService {

    Author save(AuthorDto author);

    AuthorDto getAuthorById(Long id);

}