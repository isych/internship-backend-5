package com.exadel.backendservice.mapper.role;

import com.exadel.backendservice.dto.resp.RoleRespDto;
import com.exadel.backendservice.entity.Role;
import com.exadel.backendservice.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleResponseMapper extends AbstractMapper<Role, RoleRespDto> {
    public RoleResponseMapper() {
        super(Role.class, RoleRespDto.class);
    }
}
