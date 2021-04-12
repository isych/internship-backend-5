package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.resp.DetailedEventDto;
import com.exadel.backendservice.dto.resp.SearchEventDto;
import com.exadel.backendservice.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
@Api(tags = "Контроллер для работы с анонсами событий")
public class EventController {

    private final EventService eventService;

    @ApiOperation(value = "Метод для получения списка всех типов событий")
    @GetMapping("/types")
    public ResponseEntity<List<String>> getEventTypes() {
        return new ResponseEntity<>(eventService.getEventTypes(), HttpStatus.OK);
    }

    @ApiOperation(value = "Метод для частичного извлечения списка событий")
    @GetMapping
    public ResponseEntity<Page<SearchEventDto>> getPageOfEvents(Pageable pageable) {
        return new ResponseEntity<>(eventService.getEventsPage(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Метод для получения события по Id")
    @GetMapping("/{id}")
    public ResponseEntity<DetailedEventDto> getEventById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
    }
}
