package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.req.CreateEventDto;
import com.exadel.backendservice.dto.resp.DetailedEventDto;
import com.exadel.backendservice.dto.resp.EventWithIdDto;
import com.exadel.backendservice.dto.resp.SearchEventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    EventWithIdDto saveEvent(CreateEventDto event);

    List<String> getEventTypes();

    Page<SearchEventDto> getEventsPage(Pageable pageable);

    DetailedEventDto getEvent(String name);

    Boolean isUnique(String name);
}
