package com.exadel.backendservice.entity;

import com.exadel.backendservice.model.InterviewerType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "interviewer")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Interviewer extends AbstractEntity {
    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InterviewerType type;

    @OneToMany(mappedBy = "interviewer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<InterviewerTimeslot> timeslots = new HashSet<>();

    @OneToMany(mappedBy = "interviewer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Interview> interviews = new HashSet<>();
}
