package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.dto.EventStackDto;
import com.exadel.backendservice.dto.LabelDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class EventListDto extends AbstractDto {

    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String type;

    private Set<LabelDto> labels;

    private Set<EventStackDto> eventStack;


}
