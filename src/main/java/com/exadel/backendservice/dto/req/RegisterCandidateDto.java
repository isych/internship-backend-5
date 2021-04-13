package com.exadel.backendservice.dto.req;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.model.PreferredTime;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterCandidateDto extends AbstractDto {
    private String fullName;
    private String summary;
    private String cv;
    private String email;
    private String phone;
    private String skype;
    private PreferredTime preferredTime;
    private String primaryTech;
    private String city;
    private String event;
}
