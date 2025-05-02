package com.carte.archicarte.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "T_Sinistre")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TsinistreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "N_Sinistre")
    private String NPolice;
    @Column(name = "Etat")
    private int etat;

}
