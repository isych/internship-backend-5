package com.exadel.backendservice.entity;

import com.exadel.backendservice.model.EventStatus;
import com.exadel.backendservice.model.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Event extends AbstractEntity {

    @Column(length = 64, nullable = false, unique = true)
    private String name;

    @Column(length = 256, nullable = false)
    private String description;

    @Column(name = "picture_name", length = 256)
    private String pictureName;

    @Column(name = "picture_path", length = 256)
    private String picturePath;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus = EventStatus.NOT_PUBLISHED;

    @ManyToMany(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(
            name = "event_tech",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "tech_id")}
    )
    private Set<Tech> techs = new HashSet<>();

    @OneToMany(mappedBy = "event")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Candidate> candidates = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(
            name = "event_city",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "city_id")}
    )
    private Set<City> cities = new HashSet<>();

    @OneToMany(mappedBy = "candidate")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Interview> interviews = new HashSet<>();
}
