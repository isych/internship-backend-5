package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.req.CreateEventDto;
import com.exadel.backendservice.dto.resp.DetailedEventDto;
import com.exadel.backendservice.dto.resp.EventWithIdDto;
import com.exadel.backendservice.dto.resp.SearchEventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface EventService {
    EventWithIdDto saveEvent(CreateEventDto event);

    List<String> getEventTypes();

    Page<SearchEventDto> getEventsPage(Pageable pageable);

    DetailedEventDto getEvent(String name);

    Boolean isUnique(String name);

    byte[] downloadImage(Integer id);

    Optional<EventWithIdDto> uploadCv(Integer id, MultipartFile file);
}
