package com.exadel.backendservice.services.impl;

import com.exadel.backendservice.dto.CandidateDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.repository.CandidateRepository;
import com.exadel.backendservice.repository.CityRepository;
import com.exadel.backendservice.repository.EventStackRepository;
import com.exadel.backendservice.services.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CityRepository cityRepository;
    private final EventStackRepository eventStackRepository;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository, CityRepository cityRepository, EventStackRepository eventStackRepository) {
        this.candidateRepository = candidateRepository;
        this.cityRepository = cityRepository;
        this.eventStackRepository = eventStackRepository;
    }

    @Override
    public boolean create(CandidateDto candidateDto) {
        boolean result = false;
        Candidate candidate = new Candidate();

        try {
            candidate.setFullName(candidateDto.getFullName());
            candidate.setPhone(candidateDto.getPhone());
            candidate.setEmail(candidateDto.getEmail());
            candidate.setSkype(candidateDto.getSkype());
            candidate.setCv(candidateDto.getCv());
            candidate.setCity(cityRepository.getOne(candidateDto.getCity()));
            candidate.setEventStack(eventStackRepository.getOne(candidateDto.getEventStack()));
            log.info("Candidate created: id = {}", candidate.getId());

            candidateRepository.save(candidate);
            log.info("Candidate saved: id = {}", candidate.getId());

            result = true;
        }catch (Exception ex){
            log.info("Candidate wasn't saved");
        }

        return result;
    }
}
