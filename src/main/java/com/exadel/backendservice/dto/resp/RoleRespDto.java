package com.exadel.backendservice.dto.resp;


import com.exadel.backendservice.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleRespDto extends AbstractDto {
    private UUID id;
    private String name;
}
