package com.carte.archicarte.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Sinistre_Archive")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SinistreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "N_Sinistre")
    private String NSinistre;
    @Column(name = "codeAgence")
    private Integer codeAgence;
    @Column(name = "N_Police")
    private String nPolice;
    @Column(name = "codeBranche")
    private String codebranche;
    @Column(name ="LIBELLE_BRANCHE")
    private String libBranche;
    @Column(name = "codeSousBrache")
    private String codeSousBrache;
    @Column(name = "LIBELLE_SOUS_BRANCHE")
    private String libSousBranche;
    @Column(name = "NOM_CLIENT")
    private String nomClient;
    @Column(name = "TYPE_DOSSIER")
    private String typeDossier;
    @Column(name = "ETAT_DOSSIER")
    private String etatDossier;
    @Column(name = "GESTIONNAIRE")
    private String gestionnaire;
    @Column(name = "IMMATRICULATION")
    private String immatriculation;
    @Column(name = "CODE_CLIENT")
    private String codeClient;
    @Column(name = "utilisateur")
    private String utilisateur;
    @Column(name = "date_Survenance")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date_Survenance;
    @Column(name = "date_Ouverture")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date_Ouverture;
    @Column(name = "date_Ajout")
    private Date dateAjout;
}
