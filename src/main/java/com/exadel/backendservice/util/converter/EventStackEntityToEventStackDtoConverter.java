package com.exadel.backendservice.util.converter;


import com.exadel.backendservice.dto.EventStackDto;
import com.exadel.backendservice.entity.EventStack;
import org.springframework.core.convert.converter.Converter;

public class EventStackEntityToEventStackDtoConverter implements Converter<EventStack, EventStackDto> {

    @Override
    public EventStackDto convert(EventStack entity) {
        EventStackDto dto = new EventStackDto();
        dto.setName(entity.getName());
        dto.setDescriptions(entity.getDescription());
        return dto;
    }
}
