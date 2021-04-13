package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.entity.City;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.entity.Tech;
import com.exadel.backendservice.mapper.AbstractMapper;
import com.exadel.backendservice.repository.CityRepository;
import com.exadel.backendservice.repository.EventRepository;
import com.exadel.backendservice.repository.TechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class RegisterCandidateMapper extends AbstractMapper<Candidate, RegisterCandidateDto> {

    private final CityRepository cityRepository;
    private final EventRepository eventRepository;
    private final TechRepository techRepository;

    @Autowired
    public RegisterCandidateMapper(CityRepository cityRepository, EventRepository eventRepository, TechRepository techRepository) {
        super(Candidate.class, RegisterCandidateDto.class);
        this.cityRepository = cityRepository;
        this.eventRepository = eventRepository;
        this.techRepository = techRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(RegisterCandidateDto.class, Candidate.class)
                .addMappings(m -> m.skip(Candidate::setCity))
                .addMappings(m -> m.skip(Candidate::setEvent))
                .addMappings(m -> m.skip(Candidate::setPrimaryTech))
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(RegisterCandidateDto source, Candidate destination) {
        Optional<City> cityOptional = cityRepository.findByName(source.getCity());
        Optional<Event> eventOptional = eventRepository.findByName(source.getEvent());
        Optional<Tech> techOptional = techRepository.findByName(source.getPrimaryTech());
        cityOptional.ifPresent(destination::setCity);
        eventOptional.ifPresent(destination::setEvent);
        techOptional.ifPresent(destination::setPrimaryTech);
    }
}
