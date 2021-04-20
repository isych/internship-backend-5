package com.exadel.backendservice.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "interview")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Interview extends AbstractEntity {

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(length = 100)
    private String feedback;

    @ManyToOne
    @JoinColumn(name = "interviewer_id", nullable = false)
    private Interviewer interviewer;

    @OneToMany(mappedBy = "interview")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ChecklistItemGrade> grade = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;
}
