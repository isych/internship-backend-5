package com.exadel.backendservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
@Entity
@Table(name = "candidate")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Candidate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Column(nullable = false)
    String fullName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    CandidateStatus status = CandidateStatus.YELLOW;

    @Email
    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String phone;

    @Column(nullable = false)
    String skype;

    @Column(name = "cv", nullable = false)
    String cv;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    City city;

    @ManyToOne
    @JoinColumn(name = "event_stack_id", nullable = false)
    EventStack eventStack;
}
