package com.WeBuild.BackWeBuild.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String type;
    private String category; // Ex: Estrutural, Acabamento
    private Boolean isMandatory; // Parte obrigatória (Custos Mínimos)

    private Double unitCost; // Custo unitário do material
}