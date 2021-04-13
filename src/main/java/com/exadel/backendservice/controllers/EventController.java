package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.req.CreateEventDto;
import com.exadel.backendservice.dto.resp.DetailedEventDto;
import com.exadel.backendservice.dto.resp.EventWithIdDto;
import com.exadel.backendservice.dto.resp.SearchEventDto;
import com.exadel.backendservice.exception.ApiRequestException;
import com.exadel.backendservice.exception.ApiResponseException;
import com.exadel.backendservice.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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

    @ApiOperation(value = "Метод для получения события по имени")
    @GetMapping("/{name}")
    public ResponseEntity<DetailedEventDto> getEvent(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(eventService.getEvent(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Метод проверки уникальности имени события")
    @GetMapping("/uniqueness/{name}")
    public ResponseEntity<Boolean> checkUniqueness(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(eventService.isUnique(name), HttpStatus.OK);
    }

    @ApiOperation("Метод для создания события")
    @PostMapping("/create")
    public ResponseEntity<EventWithIdDto> createEvent(@RequestBody @Valid CreateEventDto eventDto) {
        if (Objects.isNull(eventDto) || !eventService.isUnique(eventDto.getName())) {
            throw new ApiRequestException("Cannot create event. Invalid input data.");
        }
        EventWithIdDto eventWithId = eventService.saveEvent(eventDto);
        if (Objects.isNull(eventWithId)) {
            throw new ApiResponseException("Cannot create event. Internal error.");
        }
        return new ResponseEntity<>(eventWithId, HttpStatus.OK);
    }
}
