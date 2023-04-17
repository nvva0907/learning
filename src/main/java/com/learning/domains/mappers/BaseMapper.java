package com.learning.domains.mappers;

import java.util.List;

public interface BaseMapper<D, E> {
    E toEntity(D dto);

    D toDTO(E entity);

    List<D> toListDTO(List<E> entityList);

    List<E> toListEntity(List<D> dtoList);
}
