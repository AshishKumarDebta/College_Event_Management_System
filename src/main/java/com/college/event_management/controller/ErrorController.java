package com.college.event_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    // Optional: You can log the error details here for debugging
    @GetMapping("/error")
    public String handleError(HttpServletRequest request, SessionStatus currentStatus, HttpSession session) {
        // You can retrieve the status code to know what kind of error occurred
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
     // Invalidate the session
        session.invalidate();

        // Mark the session as complete
        currentStatus.setComplete();

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // Handle 404 errors
                return "error/error-404";  // Customize the view name for the error page
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // Handle 500 errors
                return "error/error-500";  // Customize the view name for the error page
            }
        }
        return "error/index";  // Default error view
    }

    public String getErrorPath() {
        return "/error";  // This is required for ErrorController
    }
}

