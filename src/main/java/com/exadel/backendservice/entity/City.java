package com.exadel.backendservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "city")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class City implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Column(name = "name", nullable = false)
    String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    Country country;

    @OneToMany(mappedBy = "cityEntity")
    private Set<Candidate> candidateEntities;
}
