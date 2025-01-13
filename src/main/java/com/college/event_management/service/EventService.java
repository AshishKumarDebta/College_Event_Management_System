package com.college.event_management.service;

import com.college.event_management.model.Event;
import com.college.event_management.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Integer id) {
        return eventRepository.findById(id);
    }

    public void deleteEventById(Integer id) {
        eventRepository.deleteById(id);
    }

    // Additional methods for searching and sorting
    public List<Event> getEventsByName(String eventName) {
        return eventRepository.findByEventNameContainingIgnoreCase(eventName);
    }

    public List<Event> getEventsByDate(LocalDate eventDate) {
        return eventRepository.findByEventDate(eventDate);
    }

    public List<Event> getEventsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findByEventDateBetween(startDate, endDate);
    }

    public List<Event> getEventsSortedByNameAsc() {
        return eventRepository.findByOrderByEventNameAsc();
    }

    public List<Event> getEventsSortedByNameDesc() {
        return eventRepository.findByOrderByEventNameDesc();
    }
}
