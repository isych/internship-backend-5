package com.exadel.backendservice.services;

import com.exadel.backendservice.dto.EventStackDto;
import com.exadel.backendservice.dto.EventWithLabelAndDirectionDto;
import com.exadel.backendservice.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    Event saveEvent(Event event);

    List<EventWithLabelAndDirectionDto> getAllEvents();

    List<String> getEventTypes();

    Page<EventStackDto> getPageOfEvents(Pageable pageable);
}
