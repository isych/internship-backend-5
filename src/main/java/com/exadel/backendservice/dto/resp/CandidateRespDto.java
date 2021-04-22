package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.entity.Candidate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CandidateRespDto extends AbstractDto {
    private String id;
    private String fullName;
    private String email;
    private String cv;
    private String cvPath;
    private String status;
}
