package com.college.event_management.repository;

import com.college.event_management.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    // Find events by name containing a search term
    List<Event> findByEventNameContainingIgnoreCase(String eventName);

    // Find events by event date
    List<Event> findByEventDate(LocalDate eventDate);

    // Find events between two dates
    List<Event> findByEventDateBetween(LocalDate startDate, LocalDate endDate);

    // Find events ordered by name (ascending)
    List<Event> findByOrderByEventNameAsc();

    // Find events ordered by name (descending)
    List<Event> findByOrderByEventNameDesc();

    // Find all events with pagination support
    Page<Event> findAll(Pageable pageable);
}
