package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.CountryDto;
import com.exadel.backendservice.entity.Country;
import com.exadel.backendservice.mapper.AbstractMapper;

public class CountryMapper extends AbstractMapper<Country, CountryDto> {

    public CountryMapper() {
        super(Country.class, CountryDto.class);
    }
}
