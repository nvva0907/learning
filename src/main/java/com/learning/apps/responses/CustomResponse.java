package com.learning.apps.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
public class CustomResponse<T> {
    private T data;
    private List<String> message;
    private String code;

    public static CustomResponse<Object> ok(Object data) {
        return CustomResponse.builder().data(data).code("200").message(Collections.singletonList("Successful")).build();
    }

    public CustomResponse(T data, List<String> message, String code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
}
