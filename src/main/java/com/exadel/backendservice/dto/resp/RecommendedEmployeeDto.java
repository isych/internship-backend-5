package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class RecommendedEmployeeDto extends AbstractDto {
    private Integer id;
    private String fullName;
    private List<TimeslotPreferenceDto> timeslotPreferences;
    private List<EmployeeInterviewDto> employeeInterviews;
}
