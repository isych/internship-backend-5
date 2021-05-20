package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class InterviewRespDto extends AbstractDto {

    private UUID id;

    private String candidate;

    private String employee;

    private LocalDateTime startTime;

    private String feedback;
}
