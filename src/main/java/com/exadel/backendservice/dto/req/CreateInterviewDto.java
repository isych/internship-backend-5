package com.exadel.backendservice.dto.req;

import com.exadel.backendservice.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateInterviewDto extends AbstractDto {

    private UUID employee;

    private UUID candidate;

    private LocalDateTime startTime;
}
