package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.resp.LocationDto;
import com.exadel.backendservice.entity.Country;
import com.exadel.backendservice.mapper.converter.LocationMapper;
import com.exadel.backendservice.repository.CountryRepository;
import com.exadel.backendservice.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final CountryRepository countryRepository;
    private final LocationMapper locationMapper;

    @Autowired
    public LocationServiceImpl(CountryRepository countryRepository, LocationMapper locationMapper) {
        this.countryRepository = countryRepository;
        this.locationMapper = locationMapper;
    }

    @Override
    public List<LocationDto> getAllCountriesWithCities() {
        List<LocationDto> locationDtos = locationMapper.toDto(countryRepository.findAll());
        log.debug("Location Dto list -> {}", locationDtos);
        return locationDtos;
    }
}
