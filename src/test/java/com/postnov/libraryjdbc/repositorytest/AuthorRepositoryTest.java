package com.postnov.libraryjdbc.repositorytest;

import com.postnov.libraryjdbc.model.Author;
import com.postnov.libraryjdbc.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Sql(value = {"/drop_data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void saveTest() {
        Author author = new Author((long) 1, "author_name", "author_surname");
        Author savedAuthor = authorRepository.save(author);
        assertEquals(author, savedAuthor);
    }
}
