package com.exadel.backendservice.util.converter;


import com.exadel.backendservice.dto.DirectionsDto;
import com.exadel.backendservice.entity.EventDirectionEntity;
import org.springframework.core.convert.converter.Converter;

public class TechDtoToTechEntityConverter implements Converter<DirectionsDto, EventDirectionEntity> {
    @Override
    public EventDirectionEntity convert(DirectionsDto directionsDto) {
        EventDirectionEntity entity = new EventDirectionEntity();
        entity.setName(directionsDto.getName());
        return entity;
    }
}
