package com.example.hospital.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="appointments", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Appointment {
    @Id
    @SequenceGenerator(
            name = "appointment-sequence",
            sequenceName = "appointment-sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "appointment-sequence",
            strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long nid;
    private Date appointmentDate;
    private String hospitalName;
    private String vaccineName;
    private boolean isVaccinated;
}
