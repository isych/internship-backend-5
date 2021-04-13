package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.resp.EventWithIdDto;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class EventWithIdMapper extends AbstractMapper<Event, EventWithIdDto> {
    public EventWithIdMapper() {
        super(Event.class, EventWithIdDto.class);
    }
}
