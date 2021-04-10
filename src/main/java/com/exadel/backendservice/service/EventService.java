package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.resp.EventListDto;
import com.exadel.backendservice.entity.Event;

import java.util.List;

public interface EventService {
    Event saveEvent(Event event);

    List<EventListDto> getAllEvents();

    List<String> getEventTypes();
}
