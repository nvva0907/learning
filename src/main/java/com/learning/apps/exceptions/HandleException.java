package com.learning.apps.exceptions;

import com.learning.apps.responses.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HandleException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CustomResponse<?> badRequestException(BadRequestException ex) {
        try {
            return new CustomResponse<>(null, ex.getMessages(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
        } catch (Exception e) {
            return new CustomResponse<>(null, ex.getMessages(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CustomResponse<?> handleRuntimeException(RuntimeException ex, HttpServletResponse response) {
        ex.printStackTrace();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new CustomResponse<>(null, Collections.singletonList("Service is having problems"), String.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CustomResponse<?> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return new CustomResponse<>(null, Collections.singletonList("File too large!"), String.valueOf(HttpStatus.EXPECTATION_FAILED.value()));
    }
}
