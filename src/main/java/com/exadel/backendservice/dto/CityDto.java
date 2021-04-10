package com.exadel.backendservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CityDto extends AbstractDto {
    private String name;
    private CountryDto country;
}
