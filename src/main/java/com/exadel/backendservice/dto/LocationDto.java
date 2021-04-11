package com.exadel.backendservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LocationDto extends AbstractDto {
    private String city;
    private String country;
}
