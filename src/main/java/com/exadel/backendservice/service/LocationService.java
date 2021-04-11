package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.resp.CountryWithCityDto;

import java.util.List;

public interface LocationService {
    List<CountryWithCityDto> getAllCountriesWithCities();

}
