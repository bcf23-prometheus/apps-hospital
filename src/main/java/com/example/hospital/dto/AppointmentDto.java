package com.example.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AppointmentDto {
    private Long nid;
    private Date appointmentDate;
    private String hospitalName;
    private String vaccineName;
}
