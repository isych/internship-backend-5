package com.exadel.backendservice.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tech_table")
@Data
@NoArgsConstructor
public class TechEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Size(min = 1, max = 30)
    private String name;

    @ManyToMany(mappedBy = "event_table")
    private List<EventEntity> events;
}
