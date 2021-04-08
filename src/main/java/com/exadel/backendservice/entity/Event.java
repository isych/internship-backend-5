package com.exadel.backendservice.entity;

import com.exadel.backendservice.model.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(length = 256, nullable = false)
    private String description;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(nullable = false)
    @Type(type = "com.exadel.backendservice.model.usertype.EventTypeUserType")
    private EventType type;

    @ManyToMany(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(
            name = "event_label",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "label_id")}
    )
    private Set<Label> labels = new HashSet<>();

    @OneToMany(mappedBy = "event")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<EventStack> eventStack = new HashSet<>();


}
