package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.req.CreateEventDto;
import com.exadel.backendservice.dto.resp.DetailedEventDto;
import com.exadel.backendservice.dto.resp.EventWithIdDto;
import com.exadel.backendservice.dto.resp.SearchEventDto;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.mapper.converter.CreateEventMapper;
import com.exadel.backendservice.mapper.converter.DetailedEventMapper;
import com.exadel.backendservice.mapper.converter.EventWithIdMapper;
import com.exadel.backendservice.mapper.converter.SearchEventMapper;
import com.exadel.backendservice.model.EventType;
import com.exadel.backendservice.repository.EventRepository;
import com.exadel.backendservice.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final DetailedEventMapper detailedEventMapper;
    private final SearchEventMapper searchEventMapper;
    private final CreateEventMapper createEventMapper;
    private final EventWithIdMapper eventWithIdMapper;

    @Override
    public EventWithIdDto saveEvent(CreateEventDto dto) {
        Event entity = createEventMapper.toEntity(dto);
        log.debug("Create entity -> {}", entity);
        EventWithIdDto eventWithIdDto = eventWithIdMapper.toDto(eventRepository.save(entity));
        log.debug("Event with id dto -> {}", eventWithIdDto);
        return eventWithIdDto;
    }

    @Override
    public List<String> getEventTypes() {
        return Arrays.stream(EventType.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SearchEventDto> getEventsPage(Pageable pageable) {
        Page<Event> page = eventRepository.findAll(pageable);
        List<SearchEventDto> eventList = page.get().map(searchEventMapper::toDto).collect(Collectors.toList());
        log.debug("SearchEventDto -> {}", eventList);
        return new PageImpl<>(eventList);
    }

    @Override
    public DetailedEventDto getEvent(String name) {

        Event event = eventRepository.findByName(name).stream().findAny().orElse(null);
        DetailedEventDto detailedEventDto = detailedEventMapper.toDto(event);
        log.debug("DetailedEventFto -> {}", detailedEventDto);
        return detailedEventDto;
    }

    @Override
    public Boolean isUnique(String name) {
        return !eventRepository.existsByName(name);
    }
}
