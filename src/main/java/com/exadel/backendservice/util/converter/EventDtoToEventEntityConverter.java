package com.exadel.backendservice.util.converter;

import com.exadel.backendservice.dto.EventWithLabelAndDirectionDto;
import com.exadel.backendservice.entity.EventEntity;
import com.exadel.backendservice.entity.EventType;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

public class EventDtoToEventEntityConverter implements Converter<EventWithLabelAndDirectionDto, EventEntity> {
    @Override
    public EventEntity convert(EventWithLabelAndDirectionDto event) {
        EventEntity entity = new EventEntity();
        entity.setName(event.getName());
        entity.setDescription(event.getDescription());
        entity.setType(EventType.valueOf(event.getType()));
        entity.setStartDate(getTimestamp(event.getStartDate()));
        entity.setEndDate(getTimestamp(event.getEndDate()));
        return entity;
    }

    private Timestamp getTimestamp(String datetime) {
        return Timestamp.valueOf(datetime.replace("T", " "));
    }
}
