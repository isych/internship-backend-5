package com.exadel.backendservice.util.converter;

import com.exadel.backendservice.dto.EventWithLabelAndDirectionDto;
import com.exadel.backendservice.entity.EventEntity;
import com.exadel.backendservice.entity.LabelEntity;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class EventEntityToEventDtoConverter implements Converter<EventEntity, EventWithLabelAndDirectionDto> {
    @Override
    public EventWithLabelAndDirectionDto convert(EventEntity entity) {
        EventWithLabelAndDirectionDto dto = new EventWithLabelAndDirectionDto();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStartDate(entity.getStartDate().toString());
        dto.setEndDate(entity.getEndDate().toString());
        dto.setType(entity.getType().toString());
        dto.setLabels(entity.getLabels().stream().map(LabelEntity::getName).collect(Collectors.toSet()));
        return dto;
    }
}
