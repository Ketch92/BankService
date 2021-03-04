package com.core.service.mapper;

public interface ToEntityMapper<E, D> {
    E mapToEntity(D dto);
}
