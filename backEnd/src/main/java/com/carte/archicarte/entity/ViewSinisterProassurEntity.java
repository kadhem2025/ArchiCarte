package com.carte.archicarte.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Entity
@Table(name = "table_sinistre_proassur")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Immutable
public class ViewSinisterProassurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUM_SEQ")
    private Integer NUM_SEQ;
    @Column(name = "NUMERO_SINISTRE")
    private String NSinister;
    @Column(name = "CODE_AGENCE")
    private  String codeAgence;
    @Column(name = "CODE_BRANCHE")
    private  String codeBranche;
    @Column(name = "LIBELLE_BRANCHE")
    private  String libBranche;
    @Column(name = "DATE_DE_SURVENANCE")
    private Date dateSurvenance;
    @Column(name = "CODE_SOUS_BRANCHE")
    private  String codeSousBranche;
    @Column(name = "LIBELLE_SOUS_BRANCHE")
    private String libSousBranche;
    @Column(name = "NUMERO_POLICE")
    private String NPolice;
    @Column(name = "CODE_CLIENT")
    private  String codeClient;
    @Column(name = "IMMATRICULATION")
    private  String immatriculation;
    @Column(name = "DATE_OUVERTURE")
    private  Date dateOuverture;
    @Column(name = "NOM_CLIENT")
    private  String nomClient;
    @Column(name = "TYPE_DOSSIER")
    private String typeDossier;
    @Column(name = "ETAT_DOSSIER")
    private  String etatDossier;
    @Column(name = "GESTIONNAIRE")
    private String gestionnaire;





}
