package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.dto.DetailedCandidateInterviewDto;
import com.exadel.backendservice.model.CandidateStatus;
import com.exadel.backendservice.model.InterviewProcess;
import com.exadel.backendservice.model.PreferredCandidateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class DetailedCandidateDto extends AbstractDto {
    private UUID id;

    private String fullName;

    private String summary;

    private CandidateStatus status;

    private String phone;

    private String email;

    private String skype;

    private String city;

    private String country;

    private String primaryTech;

    private PreferredCandidateTime preferredTime;

    private String eventName;

    private InterviewProcess interviewProcess;

    private List<DetailedCandidateInterviewDto> interviews;

}
