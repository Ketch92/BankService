package com.core.service;

public interface ToDtoMapper<D, E> {
    
    D mapToDto(E entity);
}
