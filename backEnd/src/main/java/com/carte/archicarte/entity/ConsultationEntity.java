package com.carte.archicarte.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Consultation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsultationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "N_Affaire")
    private String naffaire;
    @Column(name = "Consulter_Par")
    private String consulterPar;
    @Column(name = "Date_Consultation")
    private LocalDate dateConsultation;
    @Column(name = "Date_Retour")
    private  LocalDate dateRetour;
    @Column(name = "Date_Retour_Reel")
    private  Date dateRetourReel;
    @Column(name = "Date_Insertion")
    private LocalDateTime dateInsertion;
    @Column(name = "Archiviste_consultation")
    private  String archivisteCons;
    @Column(name = "Archiviste_Ajout")
    private  String archivisteAjout;
    @Column(name = "Type_Affaire")
    private  String typeAffaire;
    @Column(name = "Archiviste_Retour")
    private  String archivisteRetour;
}
