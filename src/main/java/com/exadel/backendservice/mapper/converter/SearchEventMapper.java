package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.LocationDto;
import com.exadel.backendservice.dto.resp.SearchEventDto;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

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
        List<LocationDto> locationDtoList = source.getCities().stream()
                .map(city -> new LocationDto(city.getName(), city.getCountry().getName()))
                .collect(Collectors.toList());
        destination.setLocations(locationDtoList);
    }
}
