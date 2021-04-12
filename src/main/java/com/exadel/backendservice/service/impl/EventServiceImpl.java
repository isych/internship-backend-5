package com.exadel.backendservice.service.impl;

import com.exadel.backendservice.dto.resp.CountryWithCityDto;
import com.exadel.backendservice.dto.resp.DetailedEventDto;
import com.exadel.backendservice.dto.resp.SearchEventDto;
import com.exadel.backendservice.entity.*;
import com.exadel.backendservice.mapper.converter.CountryWithCitiesMapper;
import com.exadel.backendservice.mapper.converter.SearchEventMapper;
import com.exadel.backendservice.model.EventType;
import com.exadel.backendservice.repository.CityRepository;
import com.exadel.backendservice.repository.CountryRepository;
import com.exadel.backendservice.repository.EventRepository;
import com.exadel.backendservice.repository.LabelRepository;
import com.exadel.backendservice.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final LabelRepository labelRepository;
    private final EventRepository eventRepository;

    private final CountryWithCitiesMapper countryWithCitiesMapper;
    private final SearchEventMapper eventMapper;

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<String> getEventTypes() {
        return Arrays.stream(EventType.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SearchEventDto> getEventsPage(Pageable pageable) {
        Page<Event> page = eventRepository.findAll(pageable);
        List<SearchEventDto> eventList = page.get().map(eventMapper::toDto).collect(Collectors.toList());
        log.debug("SearchEventDto -> {}", eventList);
        return new PageImpl<>(eventList);
    }

    @Override
    public DetailedEventDto getEventById(int id) {

        Event event = eventRepository.findById(id).stream().findAny().orElse(null);

        Set<Label> labelSet = event.getLabels();
        List<Integer> listLabelsId = labelSet.stream().map(AbstractEntity::getId).collect(Collectors.toList());
        List<Label> labels = labelRepository.findAllById(listLabelsId);

        Set<City> citySet = event.getCities();
        citySet.forEach(System.out::println);
        List<Integer> listCitiesId = citySet.stream().map(AbstractEntity::getId).collect(Collectors.toList());

        List<Country> countries = citySet.stream().map(city -> city.getCountry()).collect(Collectors.toList());
        countries.forEach(System.out::println);

        List<CountryWithCityDto> countryWithCityDtos = cityRepository.findAllById(listCitiesId).stream()
                .map(city -> new CountryWithCityDto(city.getCountry().getName(), null))
                .collect(Collectors.toList());

        List<String> labelsName = labels.stream()
                .map(Label::getName)
                .collect(Collectors.toList());

        DetailedEventDto detailedEventDto = new DetailedEventDto();

        detailedEventDto.setId(event.getId());
        detailedEventDto.setName(event.getName());
        detailedEventDto.setDescription(event.getDescription());
        detailedEventDto.setStartDate(event.getStartDate());
        detailedEventDto.setType(event.getType().name());
        detailedEventDto.setPictureUrl(event.getPictureUrl());
        detailedEventDto.setLabels(labelsName);
        detailedEventDto.setCountryWithCitiesDto(countryWithCityDtos);

        return detailedEventDto;
    }
}
