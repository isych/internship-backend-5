package com.exadel.backendservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tech")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Tech extends AbstractEntity {

    @Column(length = 64, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "techs")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Event> events = new HashSet<>();

    @OneToMany(mappedBy = "primaryTech")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Candidate> candidates = new HashSet<>();
}
