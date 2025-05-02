package com.carte.archicarte.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Entity
@Table(name = "table_cxp_proassur")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Immutable
public class ViewCxpProassurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUM_SEQ")
    private Integer NUM_SEQ;
    @Column(name = "NUMERO_DOSSIER_CXP")
    private String NCxp;
    @Column(name = "DATE_CXP")
    private Date dateCxp;
    @Column(name = "REGLEE")
    private Integer reglee;
    @Column(name = "DATE_REGLEMENT")
    private Date dateReglement;
    @Column(name = "SOMMATION")
    private String sommation;
    @Column(name = "AUXILIAIRE_SOMMATION")
    private String auxiliaireSommation;
    @Column(name = "DATE_SOMMATION")
    private Date dateSammation;
    @Column(name = "\"PROCEDURE\"")
    private String procedure;
    @Column(name = "AUXILIAIRE_PROC")
    private String auxiliareProc;
    @Column(name = "DATE_PROCEDURE")
    private Date dateProcedure;
    @Column(name = "EXECUTION")
    private String execution;
    @Column(name = "AUXILIAIRE_EXECUTION")
    private String auxiliareExection;
    @Column(name = "DATE_EXECUTION")
    private Date dateExecution;
    @Column(name = "DOSSIER_JUDICIAIRE")
    private String dossierJudiciare;
    @Column(name = "DATE_JUDICIAIRE")
    private Date datejudiciaire;
    @Column(name = "FRAIS_JUSTICE")
    private double fraisJustice;
    @Column(name = "SOMMATION_JUSTICE")
    private double sommationJustice;
    @Column(name = "HONORAIRE_AVOCAT")
    private double honoraireAvocat;
    @Column(name = "FRAIS_EXECUTION")
    private double fraisExexution;
    @Column(name = "ENCAIS_PRINC")
    private double encaisPrinc;
    @Column(name = "FRAIS")
    private double frais;
    @Column(name = "SITUATION_DOSSIER")
    private String  situationDossier;
    @Column(name = "DATE_SITUATION")
    private Date    datesituation;
    @Column(name = "OBSERVATION")
    private String  observation;
    @Column(name = "NOM")
    private String  nom;
    @Column(name = "ADRESSE")
    private String  adresse;
    @Column(name = "VILLE")
    private String  ville;
    @Column(name = "ZIP_CODE")
    private String  zipCode;
    @Column(name = "CODE_AGENCE")
    private String  codeAgence;
    @Column(name = "VEHICULE")
    private String  vehicule;
    @Column(name = "CREANCE_PRINCIPAL")
    private String  creancePrincipal;
    @Column(name = "GLOBAL_RECOUVREMENT")
    private Integer  globalRecouvrement;
    @Column(name = "CODE_COMPAGNIE")
    private Integer  codeCompagnie;
    @Column(name = "ID")
    private Integer  id;
    @Column(name = "SUIVI_PAR")
    private Integer  suiviPar;






}
