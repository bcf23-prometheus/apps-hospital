package com.example.hospital.dto;

import com.example.hospital.model.Doses;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HospitalRequest {
    private Long id;
    private String name;
    private String area;
    private int capacity_per_day;
    private List<Doses> doses;

}
