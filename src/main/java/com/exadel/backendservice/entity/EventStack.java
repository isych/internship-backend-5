package com.exadel.backendservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "event_stack")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EventStack extends AbstractEntity{

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "eventStack")
    private Set<Candidate> candidates;
}



