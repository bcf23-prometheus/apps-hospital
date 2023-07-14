package com.example.hospital.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="hospitals", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Hospital {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String area;
    private int capacity_per_day;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Doses> doses;
}
