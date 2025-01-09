package com.college.event_management.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "event")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "organizer")
    private String organizer;
    
    @Transient // This field won't be persisted in the database
    private String formattedDate;

    public String getFormattedDate() {
        return eventDate != null ? eventDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "N/A";
    }

}
