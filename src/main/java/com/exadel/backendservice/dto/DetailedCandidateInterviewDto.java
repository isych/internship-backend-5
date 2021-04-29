package com.exadel.backendservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class DetailedCandidateInterviewDto extends AbstractDto {
    private UUID id;
    private String feedback;
    private String interviewerName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
