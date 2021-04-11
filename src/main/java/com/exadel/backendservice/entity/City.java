package com.exadel.backendservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "city")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class City extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "city")
    private Set<Candidate> candidates = new HashSet<>();

    @ManyToMany(mappedBy = "cities")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Event> events = new HashSet<>();
}
