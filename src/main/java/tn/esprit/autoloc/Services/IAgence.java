package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Agence;

import java.util.List;

public interface IAgence {
    Agence ajouterAgence(Agence agence);
    void supprimerAgence(long idAgence);
    List<Agence> recupererAgences();
    Agence recupererAgenceById(long idAgence);
}