package com.core.service;

public interface ToEntityMapper<E, D> {
    E mapToEntity(D dto);
}
