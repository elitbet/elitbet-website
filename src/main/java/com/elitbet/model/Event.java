package com.elitbet.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Event")
@Setter@Getter@NoArgsConstructor@ToString
public class Event {
    public static final String NOT_STARTED = "Not Started";
    public static final String STARTED = "Started";
    public static final String FINISHED = "Finished";
    public static final String POSTPONED = "Postponed";
    @Id
    @Column(name = "EVENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_TYPE_ID",referencedColumnName = "EVENT_TYPE_ID")
    private EventType eventType;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_STATUS_ID",referencedColumnName = "EVENT_STATUS_ID")
    private EventStatus eventStatus;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATISTIC_ID",referencedColumnName = "STATISTIC_ID")
    private Statistic statistic;
    @Column(name = "FLASHSCORE_ID")
    private String flashscoreId;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="TOURNAMENT")
    private String tournament;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private List<Outcome> outcomeList = new ArrayList<>();
    @Column(name = "START_DATETIME")
    private Date startDateTime;

    public boolean notFinished() {
        return !eventStatus.getDescription().equals(EventStatus.FINISHED);
    }

    public boolean notStarted() {
        return !eventStatus.getDescription().equals(EventStatus.NOT_STARTED);
    }

    public boolean notPostponed() {
        return !eventStatus.getDescription().equals(EventStatus.POSTPONED);
    }

    public boolean isPostponed() {
        return !eventStatus.getDescription().equals(EventStatus.POSTPONED);
    }
}