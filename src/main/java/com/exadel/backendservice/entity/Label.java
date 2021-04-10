package com.exadel.backendservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "labels")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Label extends AbstractEntity {

    @Column(length = 64, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "labels")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Event> events = new HashSet<>();

}
