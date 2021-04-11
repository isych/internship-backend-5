package com.exadel.backendservice.service;

import com.exadel.backendservice.dto.resp.LocationDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LocationService {
    List<LocationDto> getAllCountriesWithCities();

}
