package com.example.hospital.repository;

import com.example.hospital.model.Appointment;
import com.example.hospital.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByNid(Long nid);
}
