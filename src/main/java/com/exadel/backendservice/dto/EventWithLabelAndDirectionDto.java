package com.exadel.backendservice.dto;

import lombok.Data;

import java.util.Set;

@Data
public class EventWithLabelAndDirectionDto {

    private Integer id;

    private String name;

    private String description;

    private String startDate;

    private String endDate;

    private String type;

    private Set<String> labels;

    private Set<DirectionsDto> directionsDtos;


}
