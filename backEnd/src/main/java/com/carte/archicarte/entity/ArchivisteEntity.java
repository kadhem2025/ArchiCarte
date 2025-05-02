package com.carte.archicarte.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Archiviste")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArchivisteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first name")
    private String firstName;
    @Column(name = "last name")
    private String lastName;
    @Column(name = "Email")
    private String email;
    @Column(name = "login")
    private String username;
    @Column(name = "password")
    private String password;
}
