package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class DetailedInterviewDto extends AbstractDto {
    private UUID id;
    private LocalDateTime interviewDate;
    private String interviewer;
    private DetailedCandidateDto candidate;
    private DetailedEventDto event;
    private String feedback;
}
