package com.exadel.backendservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LabelDto extends AbstractDto {
    private String name;
}
