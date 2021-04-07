package com.exadel.backendservice.controllers;


import com.exadel.backendservice.dto.EventWithLabelAndDirectionDto;
import com.exadel.backendservice.entity.EventEntity;
import com.exadel.backendservice.entity.EventType;
import com.exadel.backendservice.services.EventService;
import com.exadel.backendservice.services.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/event/")
@Api(tags = "Сервис для работы с анонсами событий")
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @ApiOperation(value = "Метод для получения списка всех событий")
    @GetMapping
    public ResponseEntity<List<EventWithLabelAndDirectionDto>> getAllEvents() {
        List<EventWithLabelAndDirectionDto> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @ApiOperation(value = "Метод для получения списка всех типов событий")
    @GetMapping("/types")
    public ResponseEntity<List<String>> getEventTypes() {
        return new ResponseEntity<>(eventService.getEventTypes(), HttpStatus.OK);
    }
}
