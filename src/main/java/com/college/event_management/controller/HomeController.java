package com.college.event_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.college.event_management.model.Student;
import com.college.event_management.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/login")
    public String login() {
        return "utility/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        // Check if login is for admin
        if (username.equals("admin") && password.equals("IGIT@1982")) {
            session.setAttribute("isAdmin", true);  // Store admin flag in session
            return "redirect:/admin/dashboard";
        } 
        // Check if login is for student
        else if (studentService.authenticateStudent(username, password)) {
            Student currentStudent = studentService.getStudentByEmail(username);
            session.setAttribute("isStudent", true);
            session.setAttribute("student", currentStudent);  // Store student object in session
            return "redirect:/student/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid credentials");
            return "redirect:/login";
        }
    }

    @GetMapping("/")
    public String home() {
        return "utility/index";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus status, HttpSession session) {
        // Mark the session as complete first, then invalidate it
        status.setComplete();

        // Invalidate the session after marking it complete
        session.invalidate();

        // Redirect to the login page after logout
        return "redirect:/login";
    }
    @GetMapping("/about-us")
    public String aboutUs(){
        return "utility/about_us";
    }

    @GetMapping("/contact-us")
    public String contactUs(){
        return "utility/contact_us";
    }
}
