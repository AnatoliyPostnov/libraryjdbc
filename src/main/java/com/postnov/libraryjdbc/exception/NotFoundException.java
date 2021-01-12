package com.postnov.libraryjdbc.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {

    private String message;

    public NotFoundException(String entityThatCannotBeFound) {
        this.message = String.format("%s can't not be found in db", entityThatCannotBeFound);
    }
}