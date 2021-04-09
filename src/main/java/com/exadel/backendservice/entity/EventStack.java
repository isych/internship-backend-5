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
@Table(name = "event_stack")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventStack implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description", nullable = false)
    String description;

    @OneToMany(mappedBy = "eventStackEntity")
    private Set<Candidate> candidateEntities;
}
