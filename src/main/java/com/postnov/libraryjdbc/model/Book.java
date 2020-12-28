package com.postnov.libraryjdbc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {

    Long id;
    
    String name;
    
    Long genre_id;

}
