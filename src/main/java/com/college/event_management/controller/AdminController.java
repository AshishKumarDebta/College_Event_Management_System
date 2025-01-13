package com.college.event_management.controller;

import com.college.event_management.model.Event;
import com.college.event_management.model.Student;
import com.college.event_management.service.EventService;
import com.college.event_management.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private EventService eventService; 
	
	@Autowired
	private ParticipantService participantService;
	
	
	@GetMapping("/dashboard")
	public String showdashboard(Model model) {
		model.addAttribute("events", eventService.getAllEvents());
		return "admin/admin_dashboard";
	}

	@GetMapping("/participants")
	public String showParticipants(@RequestParam(value = "id", required = false) Integer id, Model model) {
		// Get the list of participants by event ID if provided
		List<Student> participants = id != null ? participantService.findStudentsByEventId(id) : Collections.emptyList();
		model.addAttribute("participants", participants);

		// Fetch the list of events to display in the dropdown
		List<Event> events = eventService.getAllEvents();
		model.addAttribute("events", events);
		model.addAttribute("selectedEventId", id); // Keep track of the selected event
		return "admin/participants"; // Thymeleaf template name
	}
}
