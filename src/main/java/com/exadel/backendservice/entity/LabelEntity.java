package com.exadel.backendservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
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

    @Column(length = 64, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "labels")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<EventEntity> eventEntities = new HashSet<>();

}
