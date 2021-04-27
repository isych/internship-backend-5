package com.exadel.backendservice.mapper.role;

import com.exadel.backendservice.dto.resp.InterviewersByRoleDto;
import com.exadel.backendservice.dto.resp.RecommendedEmployeeDto;
import com.exadel.backendservice.entity.Role;
import com.exadel.backendservice.mapper.AbstractMapper;
import com.exadel.backendservice.mapper.employee.RecommendedEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InterviewersByRoleMapper extends AbstractMapper<Role, InterviewersByRoleDto> {
    private final RecommendedEmployeeMapper recommendedEmployeeMapper;

    @Autowired
    public InterviewersByRoleMapper(RecommendedEmployeeMapper recommendedEmployeeMapper) {
        super(Role.class, InterviewersByRoleDto.class);
        this.recommendedEmployeeMapper = recommendedEmployeeMapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Role.class, InterviewersByRoleDto.class)
                .addMappings(m -> m.skip(InterviewersByRoleDto::setEmployees))
                .setPostConverter(toDtoConverter());
    }

    @Override
    public void mapSpecificFields(Role source, InterviewersByRoleDto destination) {
        List<RecommendedEmployeeDto> employeeDtos = recommendedEmployeeMapper.toDto(source.getEmployees());
        destination.setEmployees(employeeDtos);
    }
}
