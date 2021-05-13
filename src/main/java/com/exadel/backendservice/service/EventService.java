package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.req.CreateEventDto;
import com.exadel.backendservice.dto.resp.CandidateRespDto;
import com.exadel.backendservice.dto.resp.DetailedEventDto;
import com.exadel.backendservice.dto.resp.EventRespDto;
import com.exadel.backendservice.dto.resp.EventsFilterDto;
import com.exadel.backendservice.model.EventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface EventService {
    EventRespDto saveEvent(CreateEventDto event);

    List<String> getEventTypes();

    Page<DetailedEventDto> getEventsPage(Pageable pageable);

    DetailedEventDto getEvent(UUID id);

    Boolean isUnique(String name);

    byte[] downloadImage(UUID id);

    EventRespDto uploadImage(UUID id, MultipartFile file);

    boolean hasPicture(UUID id);

    Page<DetailedEventDto> getPublishedEvents(Pageable pageable);

    DetailedEventDto publishEvent(UUID id);

    DetailedEventDto moveToArchive(UUID id);

    Page<DetailedEventDto>  getArchivedEvents(Pageable pageable);

    List<CandidateRespDto> getCandidatesFromEvent(UUID id);

    Page<EventsFilterDto> getEventsWithFilter(List<String> country, List<String> tech, List<String> type, List<String> status, Pageable pageable);

    Map<String, Object> getInfoForFilters();
}
