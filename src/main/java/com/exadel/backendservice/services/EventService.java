package com.exadel.backendservice.services;

import com.exadel.backendservice.dto.EventWithLabelAndDirectionDto;
import com.exadel.backendservice.entity.Event;

import java.util.List;

public interface EventService {
    Event saveEvent(Event event);

    List<EventWithLabelAndDirectionDto> getAllEvents();

    List<String> getEventTypes();
}
