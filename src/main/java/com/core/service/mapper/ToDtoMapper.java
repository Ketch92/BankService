package com.core.service.mapper;

public interface ToDtoMapper<D, E> {
    
    D mapToDto(E entity);
}
