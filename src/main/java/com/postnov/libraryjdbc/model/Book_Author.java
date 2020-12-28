package com.postnov.libraryjdbc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book_Author {
    
    Long id;
    
    Long book_id;
    
    Long author_id;
    
}
