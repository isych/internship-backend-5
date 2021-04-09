package com.exadel.backendservice.services.impl;

import com.exadel.backendservice.dto.EventStackDto;
import com.exadel.backendservice.dto.EventWithLabelAndDirectionDto;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.model.EventType;
import com.exadel.backendservice.repository.EventRepository;
import com.exadel.backendservice.services.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private static int eventPageCount;
    private final EventRepository eventRepository;
    private final ConversionService conversionService;

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<EventWithLabelAndDirectionDto> getAllEvents() {
        log.debug("Get all events from DB method");
        List<Event> eventsList = eventRepository.findAll();
        log.trace("Event list from DB: {}", eventsList.toString());
        List<EventWithLabelAndDirectionDto> eventDtos = eventsList.stream()
                .map(entity -> conversionService.convert(entity, EventWithLabelAndDirectionDto.class))
                .collect(Collectors.toList());
        log.trace("EventDto list: {}", eventDtos.toString());
        log.debug("Finish method");
        return eventDtos;
    }

    @Override
    public List<String> getEventTypes() {
        return Arrays.stream(EventType.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EventStackDto> getPageOfEvents(Pageable pageable) {
        //Pageable pageable = PageRequest.of(eventPageCount, 8, Sort.by("name"));

        Page<Event> page = eventRepository.findAll(pageable);
        List<EventStackDto> eventStackDtos = page.get()
                .map(event -> new EventStackDto(event.getName(), event.getDescription()))
                .collect(Collectors.toList());

        Page<EventStackDto> eventPage = new PageImpl<>(eventStackDtos);

        return eventPage;
    }
}
