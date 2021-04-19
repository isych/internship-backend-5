package com.exadel.backendservice.dto.req;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.model.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateEventDto extends AbstractDto {
    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private EventType type;

    private List<String> techs;

    private List<String> cities;
}
