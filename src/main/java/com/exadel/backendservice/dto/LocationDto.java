package com.exadel.backendservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LocationDto extends AbstractDto {
    private String city;
    private String country;
}
