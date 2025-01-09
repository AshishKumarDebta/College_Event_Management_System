package com.college.event_management.repository;

import com.college.event_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Custom query method to find a student by email
    Student findByEmail(String email);
}
