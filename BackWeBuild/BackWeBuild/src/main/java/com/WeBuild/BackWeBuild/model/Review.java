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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Quem avaliou

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company; // Empresa avaliada

    private Integer rating; // Nota de 1 a 5
    private String comment;

    private LocalDateTime reviewDate;
}