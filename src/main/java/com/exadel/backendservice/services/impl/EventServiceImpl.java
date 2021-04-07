package com.exadel.backendservice.services.impl;

import com.exadel.backendservice.dto.EventWithLabelAndDirectionDto;
import com.exadel.backendservice.entity.EventEntity;
import com.exadel.backendservice.model.EventType;
import com.exadel.backendservice.repository.EventRepository;
import com.exadel.backendservice.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final EventRepository eventRepository;
    private final ConversionService conversionService;


    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ConversionService conversionService) {
        this.eventRepository = eventRepository;
        this.conversionService = conversionService;
    }

    @Override
    public EventEntity saveEvent(EventEntity event) {
        return eventRepository.save(event);
    }

    @Override
    public List<EventWithLabelAndDirectionDto> getAllEvents() {
        LOGGER.debug("Get all events from DB method");
        List<EventEntity> eventsList = eventRepository.findAll();
        LOGGER.trace("Event list from DB: {}", eventsList.toString());
        List<EventWithLabelAndDirectionDto> eventDtos = eventsList.stream()
                .map(entity -> conversionService.convert(entity, EventWithLabelAndDirectionDto.class))
                .collect(Collectors.toList());
        LOGGER.trace("EventDto list: {}", eventDtos.toString());
        LOGGER.debug("Finish method");
        return eventDtos;
    }

    @Override
    public List<String> getEventTypes() {
        return Arrays.stream(EventType.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }
}