package com.exadel.backendservice.mapper.event;

import com.exadel.backendservice.dto.LocationDto;
import com.exadel.backendservice.dto.resp.DetailedEventDto;
import com.exadel.backendservice.dto.resp.TechDto;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class DetailedEventMapper extends AbstractMapper<Event, DetailedEventDto> {
    public DetailedEventMapper() {
        super(Event.class, DetailedEventDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Event.class, DetailedEventDto.class)
                .addMappings(m -> m.skip(DetailedEventDto::setLocations))
                .addMappings(m -> m.skip(DetailedEventDto::setTechs))
                .setPostConverter(toDtoConverter());
    }

    @Override
    public void mapSpecificFields(Event source, DetailedEventDto destination) {
        List<LocationDto> locationDtoList = source.getCities().stream()
                .map(city -> new LocationDto(city.getName(), city.getCountry().getName()))
                .collect(Collectors.toList());
        List<TechDto> techDtos = source.getTechs().stream()
                .map(tech -> new TechDto(tech.getId(), tech.getName()))
                .collect(Collectors.toList());
        destination.setLocations(locationDtoList);
        destination.setTechs(techDtos);
    }
}
