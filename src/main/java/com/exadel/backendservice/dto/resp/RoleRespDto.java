package com.exadel.backendservice.dto.resp;


import com.exadel.backendservice.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleRespDto extends AbstractDto {
    private Integer id;
    private String name;
}
