package com.exadel.backendservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@Entity
@Table(name = "candidate")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Candidate extends AbstractEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CandidateStatus status = CandidateStatus.YELLOW;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String skype;

    @Column(name = "cv", nullable = false)
    private String cv;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "event_stack_id", nullable = false)
    private EventStack eventStack;
}
