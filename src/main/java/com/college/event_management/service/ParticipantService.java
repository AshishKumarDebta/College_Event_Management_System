package com.college.event_management.service;

import com.college.event_management.model.Event;
import com.college.event_management.model.Participant;
import com.college.event_management.model.Student;
import com.college.event_management.repository.ParticipantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;
    

    public void addParticipant(Participant participant) {
        participantRepository.save(participant);
    }
    
    public void deleteById(Integer id) {
    	participantRepository.deleteById(id);
    }

    @Transactional // This ensures the method is executed within a transaction
    public void deleteByEventId(Integer eventId) {
        participantRepository.deleteByEventId(eventId);
    }

    public List<Student> findStudentsByEventId(Integer eventId){
        return participantRepository.findStudentsByEventId(eventId);
    }
    public List<Student> findStudentsNotParticipateByEventId(Integer eventId){
        return participantRepository.findStudentsNotParticipateByEventId(eventId);
    }
    public List<Event> findEventsByStudentId(Integer studentId){
        return participantRepository.findEventsByStudentId(studentId);
    }
    public List<Event> findEventsNotParticipatedByStudentId(Integer studentId){
        return participantRepository.findEventNotParticipatedByStudentId(studentId);
    }
}
