package com.exadel.backendservice.services;

import com.exadel.backendservice.dto.EventWithLabelAndDirectionDto;
import com.exadel.backendservice.entity.EventEntity;

import java.util.List;

public interface EventService {
    EventEntity saveEvent(EventEntity event);

    List<EventWithLabelAndDirectionDto> getAllEvents();
}
