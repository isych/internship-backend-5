package com.exadel.backendservice.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "checklist_item_grade")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ChecklistItemGrade extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "interview_id", nullable = false)
    private Interview interview;

    @ManyToOne
    @JoinColumn(name = "checklist_item_id", nullable = false)
    private ChecklistItem checklistItem;

    @Column(name = "grade", nullable = false, length = 20)
    private String grade;
}
