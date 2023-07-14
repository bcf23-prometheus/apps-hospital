package com.example.hospital.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "vaccined", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Vaccined {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long nid;
    private long hid;
    private String vaccineName;
    private Date lastVaccineDate;
}
