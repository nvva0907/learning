package com.learning.apps.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    String code;
    List<String> messages;

    public BadRequestException(String code, List<String> message) {
        this.code = code;
        this.messages = message;
    }

    public BadRequestException(String code, String message) {
        this.code = code;
        this.messages = Collections.singletonList(message);
    }

    public BadRequestException(String message) {
        this.code = String.valueOf(HttpStatus.BAD_REQUEST.value());
        this.messages = Collections.singletonList(message);
    }
}
