package com.exadel.backendservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee_timeslot")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeTimeslot extends AbstractEntity {
    @Column(name = "start_time", nullable = false)
    private Integer startTime;

    @Column(name = "end_time", nullable = false)
    private Integer endTime;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
