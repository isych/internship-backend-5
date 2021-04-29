package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.model.CandidateStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class SearchCandidateDto extends AbstractDto {
    private UUID id;

    private String primaryTech;

    private String fullName;

    private String event;

    private CandidateStatus status;
}
