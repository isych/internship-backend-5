package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.resp.SearchEventDto;
import com.exadel.backendservice.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event/")
@Api(tags = "Контроллер для работы с анонсами событий")
public class EventController {

    private final EventService eventService;

    @ApiOperation(value = "Метод для получения списка всех событий")
    @GetMapping
    public ResponseEntity<List<SearchEventDto>> getAllEvents() {
        List<SearchEventDto> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @ApiOperation(value = "Метод для получения списка всех типов событий")
    @GetMapping("/types")
    public ResponseEntity<List<String>> getEventTypes() {
        return new ResponseEntity<>(eventService.getEventTypes(), HttpStatus.OK);
    }
}
