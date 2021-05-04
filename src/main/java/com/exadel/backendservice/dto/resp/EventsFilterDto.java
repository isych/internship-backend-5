package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.model.EventStatus;
import com.exadel.backendservice.model.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventsFilterDto extends AbstractDto {
    private UUID id;
    private String name;
    private LocalDateTime startDate;
    private String description;
    private EventType type;
    private String country;
    private String tech;
    private EventStatus status;
}
