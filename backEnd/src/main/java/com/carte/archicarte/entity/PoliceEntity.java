package com.carte.archicarte.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Police_Archive")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PoliceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "N_Police")
    private String NPolice;
    @Column(name = "codeAgence")
    private Integer codeAgence;
    @Column(name = "codeBranche")
    private String codebranche;
    @Column(name = "libBranche")
    private String libBranche;
    @Column(name = "codeSousBrache")
    private String codeSousBrache;
    @Column(name = "libSousBranche")
    private String libSousBranche;
    @Column(name = "codeClient")
    private String codeClient;
    @Column(name = "nomClient")
    private String nomClient;
    @Column(name = "utilisateur")
    private String utilisateur;
    @Column(name = "date_Ajout")
    private Date dateAjout;
}
