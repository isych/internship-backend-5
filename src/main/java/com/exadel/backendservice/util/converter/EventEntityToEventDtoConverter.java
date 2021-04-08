package com.exadel.backendservice.util.converter;

import com.exadel.backendservice.dto.EventWithLabelAndDirectionDto;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.entity.Label;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class EventEntityToEventDtoConverter implements Converter<Event, EventWithLabelAndDirectionDto> {

    private final EventStackEntityToEventStackDtoConverter eventStackConverter;

    public EventEntityToEventDtoConverter(EventStackEntityToEventStackDtoConverter eventStackConverter) {
        this.eventStackConverter = eventStackConverter;
    }

    @Override
    public EventWithLabelAndDirectionDto convert(Event entity) {
        EventWithLabelAndDirectionDto dto = new EventWithLabelAndDirectionDto();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setType(entity.getType().toString());
        dto.setLabels(entity.getLabels().stream().map(Label::getName).collect(Collectors.toSet()));
        dto.setEventStackDtos(entity.getEventStack().stream()
                .map(eventStackConverter::convert)
                .collect(Collectors.toSet()));
        return dto;
    }


}
