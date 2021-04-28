package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class CandidateRespDto extends AbstractDto {
    private UUID id;
    private String fullName;
    private String email;
    private String cv;
    private String cvPath;
    private String status;
    private String interviewProcess;
}
