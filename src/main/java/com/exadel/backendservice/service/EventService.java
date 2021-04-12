package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.resp.DetailedEventDto;
import com.exadel.backendservice.dto.resp.SearchEventDto;
import com.exadel.backendservice.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    Event saveEvent(Event event);

    List<String> getEventTypes();

    Page<SearchEventDto> getEventsPage(Pageable pageable);

    DetailedEventDto getEventById(int id);
}
