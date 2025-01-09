package com.college.event_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.college.event_management.model.Participant;
import com.college.event_management.service.EventService;
import com.college.event_management.service.ParticipantService;

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
	
	@GetMapping("/dashboard/{id}")
	public String showParticipant(@PathVariable Integer id, Model model) {
		List<Participant> participants = participantService.getParticipationByEventId(id);
		return "admin/admin_dashboard";
	}

}
