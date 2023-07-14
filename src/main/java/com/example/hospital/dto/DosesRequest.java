package com.example.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DosesRequest {
    private String dose_name;
    private int dose_quantity;
    private String dose_distributor;
}
