package com.exadel.backendservice.mapper.converter;

import com.exadel.backendservice.dto.resp.EventListDto;
import com.exadel.backendservice.entity.Event;
import com.exadel.backendservice.mapper.AbstractMapper;
import com.exadel.backendservice.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EventMapper extends AbstractMapper<Event, EventListDto> {

    private final LabelRepository labelRepository;

    @Autowired
    public EventMapper(LabelRepository labelRepository) {
        super(Event.class, EventListDto.class);
        this.labelRepository = labelRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(EventListDto.class, Event.class)
                .addMappings(m -> m.skip(Event::setEventStack))
                .addMappings(m -> m.skip(Event::setLabels))
                .setPostConverter(toEntityConverter());
    }
}
