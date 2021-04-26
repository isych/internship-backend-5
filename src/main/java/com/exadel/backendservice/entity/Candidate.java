package com.exadel.backendservice.entity;

import com.exadel.backendservice.model.CandidateStatus;
import com.exadel.backendservice.model.InterviewProcess;
import com.exadel.backendservice.model.PreferredCandidateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "candidate")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Candidate extends AbstractEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(length = 100)
    private String summary;

    @Enumerated(EnumType.STRING)
    private PreferredCandidateTime preferredTime = PreferredCandidateTime.ANY;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InterviewProcess interviewProcess = InterviewProcess.REGISTERED;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CandidateStatus status = CandidateStatus.YELLOW;

    @Email
    @Column(nullable = false)
    private String email;

    private String phone;

    @Column(nullable = false)
    private String skype;

    @Column(name = "cv")
    private String cv;

    @Column(name = "cv_path")
    private String cvPath;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "tech_id", nullable = false)
    private Tech primaryTech;

    @OneToMany(mappedBy = "candidate")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Interview> interviews = new HashSet<>();
}
