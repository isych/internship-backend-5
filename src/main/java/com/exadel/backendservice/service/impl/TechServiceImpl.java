package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.entity.Tech;
import com.exadel.backendservice.repository.TechRepository;
import com.exadel.backendservice.service.TechService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TechServiceImpl implements TechService {

    private final TechRepository repository;

    @Override
    public List<String> getTechList() {
        return repository.findAll().stream()
                .map(Tech::getName)
                .collect(Collectors.toList());
    }

}
