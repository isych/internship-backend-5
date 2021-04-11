package com.exadel.backendservice.dto;

import com.exadel.backendservice.entity.City;
import com.exadel.backendservice.model.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SearchEventDto {
    private Integer id;
    private String name;
    private LocalDateTime startDate;
    private City city;
    private EventType type;
    private String pictureUrl;
}
