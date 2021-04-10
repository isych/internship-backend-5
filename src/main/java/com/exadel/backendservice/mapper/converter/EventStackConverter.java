package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.EventStackDto;
import com.exadel.backendservice.entity.EventStack;
import com.exadel.backendservice.mapper.AbstractMapper;

public class EventStackConverter extends AbstractMapper<EventStack, EventStackDto> {
    public EventStackConverter() {
        super(EventStack.class, EventStackDto.class);
    }
}
