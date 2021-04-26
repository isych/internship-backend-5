package com.exadel.backendservice.dto.req;

import com.exadel.backendservice.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateInterviewDto extends AbstractDto {

    private Integer interviewerId;

    private Integer candidateId;

    private LocalDateTime startTime;
}
