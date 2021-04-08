package com.exadel.backendservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EventWithLabelAndDirectionDto {

    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String type;

    private Set<String> labels;

    private Set<EventStackDto> eventStackDtos;


}
