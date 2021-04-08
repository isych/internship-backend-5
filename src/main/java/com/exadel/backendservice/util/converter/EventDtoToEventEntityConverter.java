package com.exadel.backendservice.util.converter;

import com.exadel.backendservice.dto.EventWithLabelAndDirectionDto;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.model.EventType;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventDtoToEventEntityConverter implements Converter<EventWithLabelAndDirectionDto, Event> {

    @Override
    public Event convert(EventWithLabelAndDirectionDto event) {
        Event entity = new Event();
        entity.setName(event.getName());
        entity.setDescription(event.getDescription());
        entity.setType(EventType.valueOf(event.getType()));
        entity.setStartDate(event.getStartDate());
        entity.setEndDate(event.getEndDate());
        return entity;
    }
}
