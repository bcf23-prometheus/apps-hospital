package com.example.hospital.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doses", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Doses {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String dose_name;
    private int dose_quantity;
    private String dose_distributor;

}
