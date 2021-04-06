package com.exadel.backendservice.util.converter;


import com.exadel.backendservice.dto.EventStackDto;
import com.exadel.backendservice.entity.EventStackEntity;
import org.springframework.core.convert.converter.Converter;

public class EventStackEntityToEventStackDtoConverter implements Converter<EventStackEntity, EventStackDto> {

    @Override
    public EventStackDto convert(EventStackEntity entity) {
        EventStackDto dto = new EventStackDto();
        dto.setName(entity.getName());
        dto.setDescriptions(entity.getDescription());
        return dto;
    }
}
