package com.exadel.backendservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "event_table")
@Data
@NoArgsConstructor
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Size(max = 60)
    private String name;

    @NonNull
    @Size(min = 1, max = 250)
    private String description;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @NonNull
    @Enumerated(EnumType.STRING)
    private EventType type;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Event_Label",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "label_id")}
    )
    private List<LabelEntity> labels;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Event_Tech",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "tech_id")}
    )
    private List<TechEntity> technologies;
}
