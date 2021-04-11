package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.resp.LocationDto;
import com.exadel.backendservice.entity.City;
import com.exadel.backendservice.entity.Country;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class LocationMapper extends AbstractMapper<Country, LocationDto> {

    public LocationMapper() {
        super(Country.class, LocationDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Country.class, LocationDto.class)
                .addMappings(m -> m.skip(LocationDto::setCities))
                .setPostConverter(toDtoConverter());
    }

    @Override
    public void mapSpecificFields(Country source, LocationDto destination) {
        destination.setCities(source.getCities().stream()
                .map(City::getName)
                .collect(Collectors.toList()));
    }
}
