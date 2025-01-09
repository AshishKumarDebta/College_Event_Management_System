package com.college.event_management.repository;

import com.college.event_management.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    List<Participant> findByStudentId(Integer studentId);
    
    List<Participant> findByEventId(Integer eventId);
}
