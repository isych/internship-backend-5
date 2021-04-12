package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.dto.InterviewDto;
import com.exadel.backendservice.model.CandidateStatus;
import com.exadel.backendservice.model.InterviewProcess;
import com.exadel.backendservice.model.PreferredTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DetailedCandidateDto extends AbstractDto {
    private String id;

    private String fullName;

    private String summary;

    private String cv;

    private CandidateStatus status;

    private String phone;

    private String email;

    private String skype;

    private String city;

    private String country;

    private PreferredTime preferredTime;

    private Integer eventStackId;

    private String eventStackName;

    private String eventName;

    private InterviewProcess interviewProcess;

    private List<InterviewDto> interviews;

}
