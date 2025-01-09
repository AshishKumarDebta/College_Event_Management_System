package com.college.event_management.controller;

import com.college.event_management.model.Event;
import com.college.event_management.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Create Event - Display Form for a New Event
    @GetMapping("/create")
    public String createEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "admin/create_event"; // This returns the create_event.html (same page for create/edit)
    }

    // Edit Event - Display Form with Existing Event Data
    @GetMapping("/edit/{id}")
    public String editEventForm(@PathVariable Integer id, Model model) {
        Optional<Event> eventOptional = eventService.getEventById(id);
        if (eventOptional.isPresent()) {
            model.addAttribute("event", eventOptional.get());
            return "admin/create_event"; // This returns the same create_event.html (used for both create and edit)
        } else {
            return "redirect:/admin/dashboard"; // redirects if event is not found
        }
    }

    // Handle POST for both Create and Edit
    @PostMapping("/create")
    public String createEvent(@ModelAttribute Event event) {
        eventService.saveEvent(event);
        return "redirect:/admin/dashboard"; // redirects to the events page after saving the event
    }

    @PostMapping("/edit")
    public String editEvent(@ModelAttribute Event event) {
        eventService.saveEvent(event);
        return "redirect:/admin/dashboard"; // redirects to the events page after editing the event
    }

    // Delete Event
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Integer id) {
        eventService.deleteEventById(id);
        return "redirect:/admin/dashboard"; // redirects to the events page after deleting the event
    }
}
