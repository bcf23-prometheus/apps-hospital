package com.example.hospital.repository;

import com.example.hospital.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, String> {

    Optional<Hospital> findByName(String name);
}
