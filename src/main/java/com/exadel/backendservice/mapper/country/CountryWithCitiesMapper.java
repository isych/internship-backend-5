package com.exadel.backendservice.mapper.country;

import com.exadel.backendservice.dto.resp.CountryWithCityDto;
import com.exadel.backendservice.entity.City;
import com.exadel.backendservice.entity.Country;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class CountryWithCitiesMapper extends AbstractMapper<Country, CountryWithCityDto> {

    public CountryWithCitiesMapper() {
        super(Country.class, CountryWithCityDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Country.class, CountryWithCityDto.class)
                .addMappings(m -> m.skip(CountryWithCityDto::setCities))
                .setPostConverter(toDtoConverter());
    }

    @Override
    public void mapSpecificFields(Country source, CountryWithCityDto destination) {
        destination.setCities(source.getCities().stream()
                .map(City::getName)
                .collect(Collectors.toList()));
    }
}
