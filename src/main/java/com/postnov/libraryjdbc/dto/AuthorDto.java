package com.postnov.libraryjdbc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class AuthorDto implements Serializable {

    private String name;

    private String surname;

}