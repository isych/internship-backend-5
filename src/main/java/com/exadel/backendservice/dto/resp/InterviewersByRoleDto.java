package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class InterviewersByRoleDto extends AbstractDto {
    private UUID id;
    private String name;
    private List<RecommendedEmployeeDto> employees;
}
