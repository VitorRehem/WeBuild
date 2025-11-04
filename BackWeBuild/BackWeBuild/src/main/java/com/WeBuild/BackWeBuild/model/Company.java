package com.WeBuild.BackWeBuild.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contact;
    private String type; // Empreiteira / Distribuidora
    private Double participationFeeRate; // Taxa de lucro do site (lucro)

    private Double averageRating;
}