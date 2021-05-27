package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.LocationDto;
import com.exadel.backendservice.dto.req.CreateEventDto;
import com.exadel.backendservice.dto.resp.*;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.entity.Tech;
import com.exadel.backendservice.exception.*;
import com.exadel.backendservice.mapper.candidate.CandidateResponseMapper;
import com.exadel.backendservice.mapper.event.CreateEventMapper;
import com.exadel.backendservice.mapper.event.DetailedEventMapper;
import com.exadel.backendservice.mapper.event.EventResponseMapper;
import com.exadel.backendservice.model.BucketName;
import com.exadel.backendservice.model.EventStatus;
import com.exadel.backendservice.model.EventType;
import com.exadel.backendservice.model.MimeTypes;
import com.exadel.backendservice.repository.CityRepository;
import com.exadel.backendservice.repository.EventRepository;
import com.exadel.backendservice.repository.EventRepositoryJPA;
import com.exadel.backendservice.repository.TechRepository;
import com.exadel.backendservice.service.EventService;
import com.exadel.backendservice.service.utils.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.SortDefinition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
    private final EventRepositoryJPA eventRepositoryJPA;

    private final DetailedEventMapper detailedEventMapper;
    private final CreateEventMapper createEventMapper;
    private final EventResponseMapper eventResponseMapper;
    private final CandidateResponseMapper candidateResponseMapper;
    private final FileStore fileStoreService;


    @Override
    public EventRespDto saveEvent(CreateEventDto dto) {
        if (!isUnique(dto.getName())) {
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
        EventRespDto eventRespDto = eventResponseMapper.toDto(eventRepository.save(entity));
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
    public Page<DetailedEventDto> getEventsPage(Pageable pageable) {
        Page<Event> page = eventRepository.findAll(pageable);
        List<DetailedEventDto> eventList = page.get().map(detailedEventMapper::toDto).filter(elem -> elem.getEventStatus() != EventStatus.ARCHIVED).collect(Collectors.toList());
        log.debug("SearchEventDto -> {}", eventList);
        //return new PageImpl<>(eventList);
        return new PageImpl(eventList, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()), eventList.size());
    }

    @Override
    public DetailedEventDto getEvent(UUID id) {
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
    public byte[] downloadImage(UUID id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            if (hasPicture(event)) {
                return fileStoreService.download(event.getPicturePath(), event.getPictureName());
            }
            throw new DBNotFoundException("Event does not have an image");
        } else {
            throw new DBNotFoundException(UNABLE_TO_FIND_EVENT);
        }
    }

    @Override
    public EventRespDto uploadImage(UUID id, MultipartFile file) {
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
            return eventResponseMapper.toDto(event);
        } else {
            throw new DBNotFoundException(UNABLE_TO_FIND_EVENT);
        }
    }

    @Override
    public boolean hasPicture(UUID id) {
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

    @Override
    public Page<DetailedEventDto> getPublishedEvents(Pageable pageable) {
        List<Event> eventsList = eventRepository.findByEventStatus(EventStatus.PUBLISHED, pageable);
        List<DetailedEventDto> publishedEvents = eventsList.stream()
                .map(detailedEventMapper::toDto)
                .collect(Collectors.toList());
        log.debug("SearchEventDto -> {}", publishedEvents);
        return new PageImpl<>(publishedEvents);
    }

    @Override
    public DetailedEventDto publishEvent(UUID id) {
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            if (!event.getEventStatus().equals(EventStatus.PUBLISHED)) {
                event.setEventStatus(EventStatus.PUBLISHED);
                eventRepository.save(event);
                DetailedEventDto detailedEventDto = detailedEventMapper.toDto(event);
                log.debug("DetailedEventDto -> {}", detailedEventDto);
                return detailedEventDto;
            } else {
                throw new SameEventStatusException("Event is already published");
            }
        } else {
            throw new DBNotFoundException(UNABLE_TO_FIND_EVENT);
        }
    }

    @Override
    public DetailedEventDto moveToArchive(UUID id) {
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            if (!event.getEventStatus().equals(EventStatus.ARCHIVED)) {
                event.setEventStatus(EventStatus.ARCHIVED);
                eventRepository.save(event);
                DetailedEventDto detailedEventDto = detailedEventMapper.toDto(event);
                log.debug("DetailedEventDto -> {}", detailedEventDto);
                return detailedEventDto;
            } else {
                throw new SameEventStatusException("Event is already in the archive");
            }
        } else {
            throw new DBNotFoundException(UNABLE_TO_FIND_EVENT);
        }
    }

    @Override
    public Page<DetailedEventDto> getArchivedEvents(Pageable pageable) {
        List<Event> eventsList = eventRepository.findByEventStatus(EventStatus.ARCHIVED, pageable);
        List<DetailedEventDto> publishedEvents = eventsList.stream()
                .map(detailedEventMapper::toDto)
                .collect(Collectors.toList());
        log.debug("SearchEventDto -> {}", publishedEvents);
        return new PageImpl<>(publishedEvents);
    }

    @Override
    public List<CandidateRespDto> getCandidatesFromEvent(UUID id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            List<CandidateRespDto> candidates = event.getCandidates().stream()
                    .map(candidateResponseMapper::toDto)
                    .collect(Collectors.toList());
            log.debug("CandidateRespDto -> {}", candidates);
            return candidates;
        } else {
            throw new DBNotFoundException(UNABLE_TO_FIND_EVENT);
        }
    }

    @Override
    public PageImpl getEventsWithFilter(List<String> country, List<String> tech, List<String> type, List<String> status, Pageable pageable) {
        final int start = (int) pageable.getOffset();
        Map<String, List<String>> map = new HashMap<>();
        paramsToMap(country, tech, type, status, map);
        if (map.size() != 0) {
            String param = createPartQuery(map);
            String query = "select events.id as id from ((select event_id, country.name from event_city join city on event_city.city_id = city.id join country on country.id = city.country_id) as res join events on events.id = res.event_id) join (select event_id, t.name from event_tech join tech t on t.id = event_tech.tech_id) as t on t.event_id = events.id where (" + param.replaceAll(" and", ") and").replaceAll("and ", "and (") + ")";
            Set<Event> events = eventRepositoryJPA.findAllByFilter(query).stream()
                .map(elem -> eventRepository.findById(elem.getId()).get()).collect(Collectors.toSet());
            List eventsDto = events.stream()
                    .map(elem -> new EventDto(
                            elem.getId(),
                            elem.getName(),
                            elem.getStartDate(),
                            elem.getDescription(),
                            elem.getType(),
                            elem.getCities().stream().map(city -> new LocationDto(city.getName(), city.getCountry().getName())).collect(Collectors.toList()),
                            elem.getTechs().stream().map(el -> new TechDto(el.getId(), el.getName())).collect(Collectors.toList()),
                            elem.getEventStatus()))
                    .collect(Collectors.toList());
            int end = Math.min((start + pageable.getPageSize()), eventsDto.size());
            return new PageImpl<>(eventsDto.subList(start, end), pageable, eventsDto.size());
        } else {
            List events = eventRepository.findAll()
                    .stream()
                    .filter(event -> !event.getEventStatus().equals(EventStatus.ARCHIVED))
                    .map(elem -> new EventDto(
                            elem.getId(),
                            elem.getName(),
                            elem.getStartDate(),
                            elem.getDescription(),
                            elem.getType(),
                            elem.getCities().stream().map(city -> new LocationDto(city.getName(), city.getCountry().getName())).collect(Collectors.toList()),
                            elem.getTechs().stream().map(el -> new TechDto(el.getId(), el.getName())).collect(Collectors.toList()),
                            elem.getEventStatus()))
                    .collect(Collectors.toList());
            int end = Math.min((start + pageable.getPageSize()), events.size());
            return new PageImpl<>(events.subList(start, end), pageable, events.size());
        }
    }

    private void paramsToMap(List<String> country, List<String> tech, List<String> type, List<String> status, Map<String, List<String>> map) {
        if (country != null && !country.isEmpty()) {
            map.put("res.name", country);
        }
        if (type != null && !type.isEmpty()) {
            map.put("events.type", type);
        }
        if (tech != null && !tech.isEmpty()) {
            map.put("t.name", tech);
        }
        if (status != null && !status.isEmpty()) {
            map.put("events.status", status);
        }
    }

    private String createPartQuery(Map<String, List<String>> map) {
        StringBuilder sb = new StringBuilder();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String key = (String) pair.getKey();
            List<String> elems = (List<String>) pair.getValue();
            for (String el : elems) {
                sb.append(key).append(" = '").append(el).append("' or ");
            }
            String str = sb.toString();
            String res = str.substring(0, str.length() - 3) + "and ";
            sb.delete(0, sb.length());
            sb.append(res);

        }
        String str = sb.toString();
        return str.substring(0, str.length() - 5);
    }

    @Override
    public Map<String, Object> getInfoForFilters() {
        List<Event> events = eventRepository.findAll();
        Map<String, Object> info = new HashMap<>();
        Set<String> tech = new HashSet<>();
        Set<String> countries = new HashSet<>();
        Set<EventType> type = events.stream().map(Event::getType).collect(Collectors.toSet());
        Set<EventStatus> eventStatuses = events.stream().map(Event::getEventStatus).collect(Collectors.toSet());
        events.stream().map(elem -> elem.getTechs().stream().map(Tech::getName).collect(Collectors.toSet())).forEach(tech::addAll);
        events.stream().map(elem -> elem.getCities().stream().map(el -> el.getCountry().getName()).collect(Collectors.toSet())).forEach(countries::addAll);
        info.put("tech", tech);
        info.put("country", countries);
        info.put("status", eventStatuses);
        info.put("type", type);
        return info;
    }

    @Override
    public EventRespDto editEvent(UUID id, CreateEventDto dto) {
        Event event = createEventMapper.toEntity(dto);
        event.setId(id);
        System.out.println("EVENT: : " + event);
        Event eventWithID = eventRepository.save(event);
        return eventResponseMapper.toDto(eventWithID);
    }

    @Override
    public DetailedEventDto unpublishEvent(UUID id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            if (!event.getEventStatus().equals(EventStatus.NOT_PUBLISHED)) {
                event.setEventStatus(EventStatus.NOT_PUBLISHED);
                eventRepository.save(event);
                DetailedEventDto detailedEventDto = detailedEventMapper.toDto(event);
                log.debug("DetailedEventDto -> {}", detailedEventDto);
                return detailedEventDto;
            } else {
                throw new SameEventStatusException("Event is already unpublished");
            }
        } else {
            throw new DBNotFoundException(UNABLE_TO_FIND_EVENT);
        }
    }
}
