package com.postnov.libraryjdbc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ResponseMessageDto implements Serializable {

    private String typeException;

    private String message;

}
