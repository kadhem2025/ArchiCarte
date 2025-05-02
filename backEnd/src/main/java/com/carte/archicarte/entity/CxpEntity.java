package com.carte.archicarte.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "Cxp")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CxpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "NUM_DOSSIER_CXP")
    private String NCxp;
    @Column(name = "DATE_CXP")
    private Date dateCxp;
    @Column(name = "CODE_AGENCE")
    private String codeAgence;
    @Column(name = "VEHICULE")
    private String vehicule;
    @Column(name = "date_ajout")
    private Date dateAjout;
    @Column(name="Nom_client_cxp")
    private String nomClienCxp;
    @Column(name="adresse_Client_Cxp")
    private String adresseClientCxp;
    @Column(name="Ville_Client_Cxp")
    private String villeClientcxp;
    @Column(name="observation_Cxp")
    private String observationCxp;
    @Column(name="utilisateur")
    private String utilisateur;



}
