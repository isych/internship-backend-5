package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.model.InterviewProcess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewFullInfoRespDto {

    private UUID idInterview;

    private String candidate;

    private String employee;

    private LocalDateTime interviewTime;

    private InterviewProcess interviewProcess;

    private String candidatePrimaryTech;

}
