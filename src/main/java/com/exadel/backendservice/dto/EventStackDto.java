package com.exadel.backendservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EventStackDto extends AbstractDto {
    private Integer id;
    private String name;
    private String description;
}
