package com.exadel.backendservice.dto;

import com.exadel.backendservice.model.InterviewerType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InterviewDto extends AbstractDto {
    private String feedback;
    private String interviewerName;
    private InterviewerType interviewerType;

    //TODO time
}
