package com.carte.archicarte.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "demande_personnel")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "code_dossier")
    private String codeDossier;
    @Column(name = "date_reservation")
    private LocalDate dateRes;
    @Column(name = "date_retour")
    private LocalDate dateRet;
    @Column(name = "Name_Per")
    private  String namePer;
    @Column(name = "Etat")
    private  int etat;
    @Column(name = "reservation_date")
    private String currentDate;

}
