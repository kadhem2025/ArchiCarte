package com.carte.archicarte.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDummyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;
    private String nPolice;
    private String consulterPar;
    private LocalDate dateConsultation;
    private LocalDate dateRetour;
    private int etat;
}
