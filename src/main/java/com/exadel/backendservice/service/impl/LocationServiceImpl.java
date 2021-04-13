package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.resp.CountryWithCityDto;
import com.exadel.backendservice.mapper.converter.CountryWithCitiesMapper;
import com.exadel.backendservice.repository.CountryRepository;
import com.exadel.backendservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final CountryRepository countryRepository;
    private final CountryWithCitiesMapper countryWithCitiesMapper;

    @Override
    public List<CountryWithCityDto> getAllCountriesWithCities() {
        List<CountryWithCityDto> countryWithCityDtos = countryWithCitiesMapper.toDto(countryRepository.findAll());
        log.debug("Location Dto list -> {}", countryWithCityDtos);
        return countryWithCityDtos;
    }
}
