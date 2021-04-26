package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeInterviewDto extends AbstractDto {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
