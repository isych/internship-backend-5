package com.exadel.backendservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class InterviewDto extends AbstractDto {
    private String feedback;
    private String interviewerName;
    private LocalDateTime time;
}
