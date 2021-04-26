package com.exadel.backendservice.mapper.event;

import com.exadel.backendservice.dto.resp.EventRespDto;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class EventResponseMapper extends AbstractMapper<Event, EventRespDto> {
    public EventResponseMapper() {
        super(Event.class, EventRespDto.class);
    }
}
