package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.EventStackDto;
import com.exadel.backendservice.dto.resp.EventListDto;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.mapper.converter.EventMapper;
import com.exadel.backendservice.model.EventType;
import com.exadel.backendservice.repository.EventRepository;
import com.exadel.backendservice.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<EventListDto> getAllEvents() {
        log.debug("Get all events from DB method");
        List<Event> eventsList = eventRepository.findAll();
        log.trace("Event list from DB: {}", eventsList.toString());
        List<EventListDto> eventDtos = eventMapper.toDto(eventsList);
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

        Page<Event> page = eventRepository.findAll(pageable);
        Page<EventStackDto> eventPage = new PageImpl<>(null, pageable, pageable.getPageSize());

        return eventPage;
    }
}
