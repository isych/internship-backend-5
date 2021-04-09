package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.req.RegisterCandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.entity.City;
import com.exadel.backendservice.entity.EventStack;
import com.exadel.backendservice.mapper.AbstractMapper;
import com.exadel.backendservice.repository.CityRepository;
import com.exadel.backendservice.repository.EventStackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class CandidateMapper extends AbstractMapper<Candidate, RegisterCandidateDto> {

    private final CityRepository cityRepository;
    private final EventStackRepository eventStackRepository;

    @Autowired
    public CandidateMapper(CityRepository cityRepository, EventStackRepository eventStackRepository) {
        super(Candidate.class, RegisterCandidateDto.class);
        this.cityRepository = cityRepository;
        this.eventStackRepository = eventStackRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Candidate.class, RegisterCandidateDto.class)
                .addMappings(m -> m.skip(RegisterCandidateDto::setCity))
                .addMappings(m -> m.skip(RegisterCandidateDto::setEventStack))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(RegisterCandidateDto.class, Candidate.class)
                .addMappings(m -> m.skip(Candidate::setCity))
                .addMappings(m -> m.skip(Candidate::setEventStack))
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(RegisterCandidateDto source, Candidate destination) {
        Optional<City> cityOptional = cityRepository.findByName(source.getCity().getName());
        Optional<EventStack> eventStackOptional = eventStackRepository.findById(source.getEventStack().getId());
        cityOptional.ifPresent(destination::setCity);
        eventStackOptional.ifPresent(destination::setEventStack);
    }
}
