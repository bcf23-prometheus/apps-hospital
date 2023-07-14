package com.example.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HospitalResponse {
    private String vaccinated_nid;
    private String dose_id;
    private String date_of_dose;
}
