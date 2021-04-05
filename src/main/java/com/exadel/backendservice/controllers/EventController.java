package com.exadel.backendservice.controllers;


import com.exadel.backendservice.dto.DirectionsDto;
import com.exadel.backendservice.dto.EventWithLabelAndDirectionDto;
import com.exadel.backendservice.entity.EventEntity;
import com.exadel.backendservice.entity.EventDirectionEntity;
import com.exadel.backendservice.services.EventService;
import com.exadel.backendservice.services.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event/")
@Api(tags = "Сервис для работы с анонсами событий")
public class EventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final ConversionService conversionService;
    private final EventService eventService;

    @Autowired
    public EventController(ConversionService conversionService, EventService eventService) {
        this.conversionService = conversionService;
        this.eventService = eventService;
    }

    @ApiOperation(value = "Метод для добавления события")
    @PostMapping("/add")
    public ResponseEntity<EventWithLabelAndDirectionDto> addEvent(@RequestBody EventWithLabelAndDirectionDto eventDto) {
        if (eventDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        EventEntity event = conversionService.convert(eventDto, EventEntity.class);
        EventEntity eventWithId = eventService.saveEvent(event);
        return new ResponseEntity(eventWithId, new HttpHeaders(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Метод для получения всех событий")
    @GetMapping
    public ResponseEntity<List<EventWithLabelAndDirectionDto>> getAllEvents() {
        List<EventWithLabelAndDirectionDto> events = eventService.getAllEvents();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    private List<EventDirectionEntity> getDirectionEntities(List<DirectionsDto> techDtos) {
        return techDtos.stream()
                .map(dto -> conversionService.convert(dto, EventDirectionEntity.class))
                .collect(Collectors.toList());
    }



}
