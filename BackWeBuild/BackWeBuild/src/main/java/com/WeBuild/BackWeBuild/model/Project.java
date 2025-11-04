package com.WeBuild.BackWeBuild.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client; // Cliente que criou o projeto

    private String location; // Localização do terreno
    private String typeOfConstruction; // Tipo de empreendimento
    private Double totalArea; // Metragem total do terreno
    private Double builtArea; // Área construída
    private Integer averageExecutionTimeDays; // Prazo médio de execução

    @Column(columnDefinition = "text")
    private String selectedMaterials; // Opções de materiais escolhidos

    private Double estimatedCost; // Estimativa de valores (Custos)
    private Double estimatedAppreciation; // Previsão de valorização

    private LocalDateTime submissionDate;
}