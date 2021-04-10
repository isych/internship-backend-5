package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.CityDto;
import com.exadel.backendservice.entity.City;
import com.exadel.backendservice.mapper.AbstractMapper;

public class CityMapper extends AbstractMapper<City, CityDto> {

    public CityMapper() {
        super(City.class, CityDto.class);
    }
}
