package com.exadel.backendservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "dynamic_interview_link")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DynamicInterviewLink extends AbstractEntity {

    @Column(nullable = false)
    private String code;

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @Column(name = "interview_id", nullable = false)
    private UUID interviewId;
}
