package com.exadel.backendservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends AbstractEntity {

    @NonNull
    @Size(min = 1, max = 60)
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role roleEntity;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Email
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "employee")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<EmployeeTimeslot> timeslots = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Interview> interviews = new HashSet<>();
}
