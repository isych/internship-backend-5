package com.exadel.backendservice.dto.resp;


import com.exadel.backendservice.dto.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TechDto extends AbstractDto {
    private UUID id;
    private String name;
}
