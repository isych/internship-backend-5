package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.resp.LocationDto;
import com.exadel.backendservice.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/location/")
@Api(tags = "Контроллер для работы со странами и городами")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    @ApiOperation(value = "Метод для получения списка со странами и их городами")
    public ResponseEntity<List<LocationDto>> getCountriesWithCitiesDto() {
        List<LocationDto> locationDtos = locationService.getAllCountriesWithCities();
        return new ResponseEntity<>(locationDtos, HttpStatus.OK);
    }
}
