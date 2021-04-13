package com.exadel.backendservice.entity;

import com.exadel.backendservice.model.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

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

    @Column(length = 64, nullable = false)
    private String name;

    @Column(length = 256, nullable = false)
    private String description;

    @Column(length = 256)
    private String pictureUrl;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType type;

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

}
