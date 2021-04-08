package com.exadel.backendservice.services.impl;

import com.exadel.backendservice.dto.CandidateDto;
import com.exadel.backendservice.entity.CandidateEntity;
import com.exadel.backendservice.repository.CandidateRepository;
import com.exadel.backendservice.repository.CityRepository;
import com.exadel.backendservice.repository.EventStackRepository;
import com.exadel.backendservice.services.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CandidateServiceImpl implements CandidateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateServiceImpl.class); // @slf4g ?????????????????

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

        try {
            CandidateEntity candidateEntity = new CandidateEntity();

            candidateEntity.setFullName(candidateDto.getFullName());
            candidateEntity.setPhone(candidateDto.getPhone());
            candidateEntity.setEmail(candidateDto.getEmail());
            candidateEntity.setSkype(candidateDto.getSkype());
            candidateEntity.setCv(candidateDto.getCv());
            candidateEntity.setCityEntity(cityRepository.getOne(candidateDto.getCity()));
            candidateEntity.setEventStackEntity(eventStackRepository.getOne(candidateDto.getEventStack()));

            candidateRepository.save(candidateEntity);

            LOGGER.info("User created: ID = " + candidateEntity.getId());  // @slf4g ?????????????????

        }catch (Exception ex){
            return false;
        }

        return true;
    }
}
