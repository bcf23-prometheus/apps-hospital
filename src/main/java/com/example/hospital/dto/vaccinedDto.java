package com.example.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class vaccinedDto {
    private Long nid;
    private long hid;
    private String vaccineName;
    private Date lastVaccineDate;
}
