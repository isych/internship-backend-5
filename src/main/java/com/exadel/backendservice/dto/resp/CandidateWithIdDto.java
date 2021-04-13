package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.model.CandidateStatus;
import com.exadel.backendservice.model.PreferredTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CandidateWithIdDto extends AbstractDto {
    private String id;

    private String fullName;

    private CandidateStatus status;

    private String email;

    private String city;

    private PreferredTime preferredTime;

}
