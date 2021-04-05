package com.exadel.backendservice.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "label_table")
@Data
@NoArgsConstructor
public class LabelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Size(min = 1, max = 30)
    private String name;

    @Transient
    @ManyToMany(mappedBy = "labels", fetch = FetchType.EAGER)
    private Set<EventEntity> eventEntities = new HashSet<>();
    
}
