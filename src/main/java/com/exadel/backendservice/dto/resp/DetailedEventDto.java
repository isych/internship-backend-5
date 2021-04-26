package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.dto.LocationDto;
import com.exadel.backendservice.model.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DetailedEventDto extends AbstractDto {
    private Integer id;
    private String name;
    private LocalDateTime startDate;
    private String description;
    private EventType type;
    private List<LocationDto> locations;
}
