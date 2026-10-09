package tn.esprit.autoloc.Entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.Set;

@Entity
@Table(name = "Agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 50)
    private String ville;

    @Column(nullable = false, length = 255)
    private String adresse;

    @Column(nullable = false, length = 20)
    private String telephone;

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    private Set<Vehicules> vehicules;

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    private Set<Employe> employes;
}