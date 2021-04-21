package com.exadel.backendservice.dto.req;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.model.PreferredCandidateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterCandidateDto extends AbstractDto {
    private String fullName;
    private String summary;
    private String email;
    private String phone;
    private String skype;
    private PreferredCandidateTime preferredTime;
    private String primaryTech;
    private String city;
    private String event;
}
