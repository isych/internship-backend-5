package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.req.CreateEventDto;
import com.exadel.backendservice.dto.resp.DetailedEventDto;
import com.exadel.backendservice.dto.resp.EventRespDto;
import com.exadel.backendservice.dto.resp.SearchEventDto;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.exception.CannotUploadFileException;
import com.exadel.backendservice.exception.DBNotFoundException;
import com.exadel.backendservice.exception.DBNotUniqueValueForUniqueFieldException;
import com.exadel.backendservice.exception.UnsupportedMediaFormatException;
import com.exadel.backendservice.mapper.converter.CreateEventMapper;
import com.exadel.backendservice.mapper.converter.DetailedEventMapper;
import com.exadel.backendservice.mapper.converter.EventWithIdMapper;
import com.exadel.backendservice.mapper.converter.SearchEventMapper;
import com.exadel.backendservice.model.BucketName;
import com.exadel.backendservice.model.EventType;
import com.exadel.backendservice.model.MimeTypes;
import com.exadel.backendservice.repository.CityRepository;
import com.exadel.backendservice.repository.EventRepository;
import com.exadel.backendservice.repository.TechRepository;
import com.exadel.backendservice.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final String UNABLE_TO_FIND_EVENT = "Unable to find event";

    private final EventRepository eventRepository;
    private final CityRepository cityRepository;
    private final TechRepository techRepository;

    private final DetailedEventMapper detailedEventMapper;
    private final SearchEventMapper searchEventMapper;
    private final CreateEventMapper createEventMapper;
    private final EventWithIdMapper eventWithIdMapper;
    private final FileStoreServiceImpl fileStoreService;


    @Override
    public EventRespDto saveEvent(CreateEventDto dto) {
        if(!isUnique(dto.getName())) {
            throw new DBNotUniqueValueForUniqueFieldException("This name for events is used");
        }
        if (!cityRepository.existsAllByNameIn(dto.getCities())) {
            throw new DBNotFoundException("One or more cities is not exists");
        }
        if (!techRepository.existsAllByNameIn(dto.getTechs())) {
            throw new DBNotFoundException("One or more technologies is not exists");
        }
        Event entity = createEventMapper.toEntity(dto);
        log.debug("Create entity -> {}", entity);
        EventRespDto eventRespDto = eventWithIdMapper.toDto(eventRepository.save(entity));
        log.debug("Event with id dto -> {}", eventRespDto);
        return eventRespDto;

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
    public DetailedEventDto getEvent(Integer id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            DetailedEventDto detailedEventDto = detailedEventMapper.toDto(event);
            log.debug("DetailedEventFto -> {}", detailedEventDto);
            return detailedEventDto;
        } else {
            throw new DBNotFoundException(UNABLE_TO_FIND_EVENT);
        }
    }

    @Override
    public Boolean isUnique(String name) {
        return !eventRepository.existsByName(name);
    }

    @Override
    public byte[] downloadImage(Integer id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            if (hasPicture(event)){
                return fileStoreService.download(event.getPicturePath(), event.getPictureName());
            }
            throw new DBNotFoundException("Event does not have an image");
        } else {
            throw new DBNotFoundException(UNABLE_TO_FIND_EVENT);
        }
    }

    @Override
    public EventRespDto uploadImage(Integer id, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        if (!MimeTypes.isImage(Objects.requireNonNull(file.getContentType()))) {
            throw new UnsupportedMediaFormatException("Uploaded file is not image");
        }
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            String path = String.format("%s/%s", BucketName.EVENT_PIC.getBucketName(), UUID.randomUUID());
            String fileName = String.format("%s", file.getOriginalFilename());
            try {
                fileStoreService.upload(path, fileName, Optional.of(fileStoreService.addMetadata(file)), file.getInputStream());
            } catch (IOException e) {
                throw new CannotUploadFileException("Failed to upload image", e);
            }
            Event event = eventOptional.get();
            event.setPictureName(fileName);
            event.setPicturePath(path);
            eventRepository.save(event);
            return eventWithIdMapper.toDto(event);
        } else {
            throw new DBNotFoundException(UNABLE_TO_FIND_EVENT);
        }
    }

    @Override
    public boolean hasPicture(Integer id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            return hasPicture(eventOptional.get());
        }
        throw new DBNotFoundException(UNABLE_TO_FIND_EVENT);
    }

    private boolean hasPicture(Event event) {
        if (Objects.isNull(event.getPicturePath()) || Objects.isNull(event.getPictureName())) {
            return false;
        }
        return !event.getPicturePath().isEmpty() && !event.getPictureName().isEmpty();
    }
}
