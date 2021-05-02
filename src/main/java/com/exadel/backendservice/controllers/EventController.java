package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.req.CreateEventDto;
import com.exadel.backendservice.dto.resp.DetailedEventDto;
import com.exadel.backendservice.dto.resp.EventRespDto;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
@Api(tags = "Контроллер для работы с анонсами событий")
public class EventController {

    private final EventService eventService;

    @ApiOperation(value = "Метод для получения списка всех типов событий(INTERNSHIP | MEETUP | TRAINING)")
    @GetMapping("/types")
    public ResponseEntity<List<String>> getEventTypes() {
        return new ResponseEntity<>(eventService.getEventTypes(), HttpStatus.OK);
    }

    @ApiOperation(value = "Метод для постраничного получения списка событий")
    @GetMapping
    public ResponseEntity<Page<DetailedEventDto>> getPageOfEvents(Pageable pageable) {
        return new ResponseEntity<>(eventService.getEventsPage(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Метод для получения подробной информации о событии по id")
    @GetMapping("/{id}")
    public ResponseEntity<DetailedEventDto> getEvent(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(eventService.getEvent(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Метод проверки уникальности имени события в системе")
    @GetMapping("/uniqueness/{name}")
    public ResponseEntity<Boolean> checkUniqueness(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(eventService.isUnique(name), HttpStatus.OK);
    }

    @ApiOperation("Метод для создания события")
    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody @Valid CreateEventDto eventDto) {
        if (Objects.isNull(eventDto)) {
            throw new ApiRequestException("Event is null");
        }
        EventRespDto eventWithId = eventService.saveEvent(eventDto);
        if (Objects.isNull(eventWithId)) {
            throw new ApiResponseException("Cannot create event. Internal error.");
        }
        return new ResponseEntity<>(eventWithId, HttpStatus.OK);
    }

    @ApiOperation("Метод для загрузки изображения события на сервер. Доступные форматы: jpeg, png, gif, bmp Принимает id события которое возвращается после создания события")
    @PostMapping("/{id}/image/upload")
    public ResponseEntity<EventRespDto> uploadImage(@RequestParam("id") UUID id, @RequestPart("file") MultipartFile file) {
        return new ResponseEntity<>(eventService.uploadImage(id, file), HttpStatus.OK);
    }

    @ApiOperation("Метод для скачивания изображения")
    @GetMapping(value = "{id}/image/download")
    public byte[] downloadImage(@PathVariable("id") UUID id) {
        return eventService.downloadImage(id);
    }

    @ApiOperation("Метод для проверки наличия картинки у события")
    @GetMapping(value = "{id}/image/exists")
    public ResponseEntity<Boolean> checkImage(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(eventService.hasPicture(id), HttpStatus.OK);
    }

    @ApiOperation("Метод для получения опубликованных событий постранично")
    @GetMapping("/published")
    public ResponseEntity<Page<DetailedEventDto>> getPublishedEvents(Pageable pageable) {
        return new ResponseEntity<>(eventService.getPublishedEvents(pageable), HttpStatus.OK);
    }

    @ApiOperation("Метод для публикации события")
    @GetMapping("{id}/publish")
    public ResponseEntity<DetailedEventDto> publishEvent(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(eventService.publishEvent(id) , HttpStatus.OK);
    }

    @ApiOperation("Метод для перемещения события в архив")
    @GetMapping("{id}/archive")
    public ResponseEntity<DetailedEventDto> eventToArchive(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(eventService.moveToArchive(id) , HttpStatus.OK);
    }

    @ApiOperation("Метод для получения архивных ивентов")
    @GetMapping("archived")
    public ResponseEntity<Page<DetailedEventDto>> getArchivedEvents(Pageable pageable){
        return new ResponseEntity<>(eventService.getArchivedEvents(pageable), HttpStatus.OK);
    }
}
