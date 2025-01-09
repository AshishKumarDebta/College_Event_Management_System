package com.college.event_management.service;

import com.college.event_management.model.Participant;
import com.college.event_management.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    public List<Participant> getParticipationByStudentId(Integer studentId) {
        return participantRepository.findByStudentId(studentId);
    }
    
    public List<Participant> getParticipationByEventId(Integer eventId) {
        return participantRepository.findByEventId(eventId);
    }

    public void addParticipant(Participant participant) {
        participantRepository.save(participant);
    }
    
    public void deleteById(Integer id) {
    	participantRepository.deleteById(id);
    }
}
