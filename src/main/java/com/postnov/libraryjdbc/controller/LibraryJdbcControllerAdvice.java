package com.postnov.libraryjdbc.controller;

import com.postnov.libraryjdbc.dto.ResponseMessageDto;
import com.postnov.libraryjdbc.exception.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@ControllerAdvice
@RequestMapping
@Log4j2
public class LibraryJdbcControllerAdvice {

    @ExceptionHandler(SQLException.class)
    ResponseEntity<ResponseMessageDto> sqlException(RuntimeException re) {
        ResponseMessageDto responseMessage = ResponseMessageDto.builder()
                .message(re.getLocalizedMessage())
                .typeException("SQLException")
                .build();
        log.error(re);
        return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ResponseMessageDto> notFoundException(NotFoundException re) {
        ResponseMessageDto responseMessage = ResponseMessageDto.builder()
                .message(re.getMessage())
                .typeException("NotFoundException")
                .build();
        log.error(re);
        return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}