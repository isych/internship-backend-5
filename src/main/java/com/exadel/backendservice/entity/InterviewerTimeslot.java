package com.exadel.backendservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interviewer_timeslot")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InterviewerTimeslot extends AbstractEntity {
    @Column(name = "start_time", nullable = false)
    private Integer startTime;

    @Column(name = "end_time", nullable = false)
    private Integer endTime;

    @ManyToOne
    @JoinColumn(name = "interviewer_id", nullable = false)
    private Interviewer interviewer;
}
