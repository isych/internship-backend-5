package com.exadel.backendservice.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "country")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountryEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Column(name = "name", nullable = false)
    String name;

    @OneToMany(mappedBy = "countryEntity")
    private Set<CityEntity> cityEntities;

}
