package com.exadel.backendservice.mapper;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.entity.AbstractEntity;

import java.util.Collection;
import java.util.List;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {
    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(Collection<D> dtos);

    List<D> toDto(Collection<E> entities);
}
