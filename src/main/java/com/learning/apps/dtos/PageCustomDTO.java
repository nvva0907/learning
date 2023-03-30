package com.learning.apps.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageCustomDTO<E> implements Serializable {
    List<E> content;
    int number;
    int size;
    long totalElements;
    long totalPages;
}
