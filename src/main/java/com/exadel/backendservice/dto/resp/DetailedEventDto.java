package com.exadel.backendservice.dto.resp;

import com.exadel.backendservice.dto.AbstractDto;
import com.exadel.backendservice.entity.City;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class DetailedEventDto extends AbstractDto {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private String type;
    private String pictureUrl;
    private List<String> labels;
    private List<CountryWithCityDto> countryWithCitiesDto;
}
