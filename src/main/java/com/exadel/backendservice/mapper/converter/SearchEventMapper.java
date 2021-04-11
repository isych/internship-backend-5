package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.LocationDto;
import com.exadel.backendservice.dto.resp.SearchEventDto;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchEventMapper extends AbstractMapper<Event, SearchEventDto> {
    public SearchEventMapper() {
        super(Event.class, SearchEventDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Event.class, SearchEventDto.class)
                .addMappings(m -> m.skip(SearchEventDto::setLocations))
                .setPostConverter(toDtoConverter());
    }

    @Override
    public void mapSpecificFields(Event source, SearchEventDto destination) {
        List<LocationDto> locations = new ArrayList<>();

        source.getCities().forEach(city -> {
            LocationDto locationDto = new LocationDto();
            locationDto.setCity(city.getName());
            locationDto.setCountry(city.getCountry().getName());
            locations.add(locationDto);
        });
        destination.setLocations(locations);
    }
}
