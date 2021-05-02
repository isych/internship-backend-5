package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeTimeslotDto extends AbstractDto {
    private UUID id;
    private Integer startTime;
    private Integer endTime;
}
