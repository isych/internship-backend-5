package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.req.CreateEventDto;
import com.exadel.backendservice.dto.resp.DetailedEventDto;
import com.exadel.backendservice.dto.resp.EventRespDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {
    EventRespDto saveEvent(CreateEventDto event);

    List<String> getEventTypes();

    Page<DetailedEventDto> getEventsPage(Pageable pageable);

    DetailedEventDto getEvent(Integer name);

    Boolean isUnique(String name);

    byte[] downloadImage(Integer id);

    EventRespDto uploadImage(Integer id, MultipartFile file);

    boolean hasPicture(Integer id);

    Page<DetailedEventDto> getPublishedEvents(Pageable pageable);

    DetailedEventDto publishEvent(Integer id);

    DetailedEventDto moveToArchive(Integer id);
}
