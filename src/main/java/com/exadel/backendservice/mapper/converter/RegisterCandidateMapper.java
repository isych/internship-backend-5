package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.entity.City;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.mapper.AbstractMapper;
import com.exadel.backendservice.repository.CityRepository;
import com.exadel.backendservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class RegisterCandidateMapper extends AbstractMapper<Candidate, RegisterCandidateDto> {

    private final CityRepository cityRepository;
    private final EventRepository eventRepository;

    @Autowired
    public RegisterCandidateMapper(CityRepository cityRepository, EventRepository eventRepository) {
        super(Candidate.class, RegisterCandidateDto.class);
        this.cityRepository = cityRepository;
        this.eventRepository = eventRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(RegisterCandidateDto.class, Candidate.class)
                .addMappings(m -> m.skip(Candidate::setCity))
                .addMappings(m -> m.skip(Candidate::setEvent))
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(RegisterCandidateDto source, Candidate destination) {
        Optional<City> cityOptional = cityRepository.findByName(source.getCity());
        Optional<Event> eventOptional = eventRepository.findById(source.getEventId());
        cityOptional.ifPresent(destination::setCity);
        eventOptional.ifPresent(destination::setEvent);
    }
}
