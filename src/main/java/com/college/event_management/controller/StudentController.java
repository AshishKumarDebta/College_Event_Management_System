package com.college.event_management.controller;

import com.college.event_management.model.Event;
import com.college.event_management.model.Participant;
import com.college.event_management.model.Student;
import com.college.event_management.service.EventService;
import com.college.event_management.service.ParticipantService;
import com.college.event_management.service.StudentService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/student")
@SessionAttributes("currentStudent")
public class StudentController {

	@Autowired
	private ParticipantService participantService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private EventService eventService;

	// Display the registration form (GET request)
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		// This can be used to pass attributes to the view if necessary
		return "student/register"; // This refers to the HTML file (Thymeleaf template)
	}

	// Handle form submission (POST request)
	@PostMapping("/register")
	public String handleRegistration(@RequestParam String name, @RequestParam String email,
			@RequestParam String password, @RequestParam String department, @RequestParam String year, Model model) {

		// Create a new Student object from the form data
		Student student = new Student();
		student.setName(name);
		student.setEmail(email);
		student.setDepartment(department);
		student.setPassword(password);
		student.setYear(year);

		// Call the service method to save the student (you can implement the service
		// logic yourself)
		studentService.saveStudent(student);

		// Add a success message
		model.addAttribute("message", "Registration successful!");

		// Redirect or return to the registration page with a success message
		return "redirect:/login"; // You can customize the redirection URL
	}

	@GetMapping("/dashboard")
	public String showDashboard(Model model, HttpSession session) {
		Student student = (Student) session.getAttribute("student");
		if (student == null) {
			model.addAttribute("error", "Student not found");
			return "redirect:/login";
		}

		model.addAttribute("currentStudent", student);

		List<Event> availableEvents = participantService.findEventsNotParticipatedByStudentId(student.getId());
		List<Event> participatedEvents = participantService.findEventsByStudentId(student.getId());

		model.addAttribute("participatedEvents", participatedEvents);
		model.addAttribute("availableEvents", availableEvents);

		return "student/student_dashboard";
	}

	@PostMapping("/add-event")
	public String showAddEventForm(@RequestParam Integer eventId, Model model, HttpSession session) {
		Student student = (Student) session.getAttribute("student");
		if (student == null) {
			model.addAttribute("error", "Student not found");
			return "redirect:/login";
		}
		
		Participant participant = new Participant();
		Optional<Event> optionalEvent = eventService.getEventById(eventId);
		if(optionalEvent.isPresent()){
			participant.setEvent(optionalEvent.get());
		} else{
			return "error/error-500";
		}
		participant.setStudent(student);
		participantService.addParticipant(participant);
		
		return "redirect:/student/dashboard";
	}

	@PostMapping("/delete/{id}")
	public String deleteEvent(@PathVariable Integer id, HttpSession session) {
		participantService.deleteByEventId(id);
		return "redirect:/student/dashboard";
	}

}
