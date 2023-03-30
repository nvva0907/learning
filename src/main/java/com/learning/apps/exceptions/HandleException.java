package com.learning.apps.exceptions;

import com.learning.apps.responses.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HandleException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public CustomResponse<?> badRequestException(BadRequestException ex) {
        try {
            return new CustomResponse<>(null, ex.getMessages(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
        } catch (Exception e) {
            return new CustomResponse<>(null, ex.getMessages(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public CustomResponse<?> handleRuntimeException(RuntimeException ex, HttpServletResponse response) {
        ex.printStackTrace();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new CustomResponse<>(null, Collections.singletonList("Service is having problems"), String.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public CustomResponse<?> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return new CustomResponse<>(null, Collections.singletonList("File too large!"), String.valueOf(HttpStatus.EXPECTATION_FAILED.value()));
//        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
    }
}
