package com.example.hospital.repository;

import com.example.hospital.model.Vaccined;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VaccinedRepo extends JpaRepository<Vaccined, Long> {
    boolean existsByNid(Long nid);
}
