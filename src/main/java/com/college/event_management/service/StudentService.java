package com.college.event_management.service;

import com.college.event_management.model.Student;
import com.college.event_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Method to get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Method to save a student
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }
    
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    // Method to delete a student by ID
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    public boolean authenticateStudent(String email, String password) {
        Student student = studentRepository.findByEmail(email);
        if (student != null && student.getPassword().equals(password)) {
            return true; // Authentication successful
        }
        return false; // Authentication failed
    }
}

