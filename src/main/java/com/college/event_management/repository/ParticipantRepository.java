package com.college.event_management.repository;

import com.college.event_management.model.Event;
import com.college.event_management.model.Participant;
import com.college.event_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

    @Query("SELECT p.student FROM Participant p WHERE p.event.id = :eventId")
    List<Student> findStudentsByEventId(@Param("eventId") Integer eventId);

    @Query("SELECT s FROM Student s WHERE s.id NOT IN (SELECT p.student.id FROM Participant p WHERE p.event.id = :eventId)")
    List<Student> findStudentsNotParticipateByEventId(@Param("eventId") Integer eventId);

    @Query("SELECT p.event FROM Participant p WHERE p.student.id = :studentId")
    List<Event> findEventsByStudentId(@Param("studentId") Integer studentId);

    @Query("SELECT e FROM Event e WHERE e.id NOT IN (SELECT p.event.id FROM Participant p WHERE p.student.id = :studentId)")
    List<Event> findEventNotParticipatedByStudentId(@Param("studentId") Integer studentId);


    @Modifying // Indicates this is a modifying query (update/delete)
    @Query("DELETE FROM Participant p WHERE p.event.id = :eventId")
    void deleteByEventId(Integer eventId);
}