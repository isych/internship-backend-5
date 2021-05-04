package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.LocationDto;
import com.exadel.backendservice.model.EventStatus;
import com.exadel.backendservice.model.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class EventDto {
    private UUID id;
    private String name;
    private LocalDateTime startDate;
    private String description;
    private EventType type;
    private List<LocationDto> locations;
    private List<TechDto> techs;
    private EventStatus eventStatus;
}
