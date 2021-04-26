package com.exadel.backendservice.mapper.event;

import com.exadel.backendservice.dto.req.CreateEventDto;
import com.exadel.backendservice.entity.City;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.entity.Tech;
import com.exadel.backendservice.mapper.AbstractMapper;
import com.exadel.backendservice.repository.CityRepository;
import com.exadel.backendservice.repository.TechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class CreateEventMapper extends AbstractMapper<Event, CreateEventDto> {

    private final CityRepository cityRepository;
    private final TechRepository techRepository;

    @Autowired
    public CreateEventMapper(CityRepository cityRepository, TechRepository techRepository) {
        super(Event.class, CreateEventDto.class);
        this.cityRepository = cityRepository;
        this.techRepository = techRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(CreateEventDto.class, Event.class)
                .addMappings(m -> m.skip(Event::setTechs))
                .addMappings(m -> m.skip(Event::setCities))
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(CreateEventDto source, Event destination) {
        Set<City> cities = cityRepository.findAllByNameIn(source.getCities());
        Set<Tech> techs = techRepository.findAllByNameIn(source.getTechs());
        destination.setCities(cities);
        destination.setTechs(techs);
    }
}
