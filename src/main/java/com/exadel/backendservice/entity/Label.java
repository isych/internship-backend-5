package com.exadel.backendservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "labels")
@Data
@NoArgsConstructor
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "labels")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Event> eventEntities = new HashSet<>();

}
