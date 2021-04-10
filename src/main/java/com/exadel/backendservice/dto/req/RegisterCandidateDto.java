package com.exadel.backendservice.dto.req;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.dto.CityDto;
import com.exadel.backendservice.dto.EventStackDto;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterCandidateDto extends AbstractDto {
    private String fullName;
    private String email;
    private String phone;
    private String skype;
    private String cv;
    private CityDto city;
    private EventStackDto eventStack;
}
