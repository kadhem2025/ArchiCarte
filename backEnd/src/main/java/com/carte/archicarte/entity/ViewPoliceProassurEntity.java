package com.carte.archicarte.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "table_police_proassur")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Immutable
@Cacheable
public class ViewPoliceProassurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUM_SEQ")
    private Integer NUM_SEQ;
    @Column(name = "NUMERO_POLICE")
    private String NPolice;
    @Column(name = "CODE_AGENCE")
    private  String codeAgence;
    @Column(name = "CODE_BRANCHE")
    private  String codeBranche;
    @Column(name = "LIBELLE_BRANCHE")
    private  String libBranche;
    @Column(name = "CODE_SOUS_BRANCHE")
    private  String codeSousBranche;
    @Column(name = "LIBELLE_SOUS_BRANCHE")
    private  String libSousBranche;
    @Column(name = "CODE_CLIENT")
    private  String codeClient;
    @Column(name = "NOM_CLIENT")
    private  String nomClient;





}
