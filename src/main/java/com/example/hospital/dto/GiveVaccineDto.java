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
public class GiveVaccineDto {
    private Long nid;
    private String vaccineName;
    private String hospitalName;
    private Date receivedDate;
}
