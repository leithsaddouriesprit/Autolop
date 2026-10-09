package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Contrat;

import java.util.List;

public interface IContrat {
    Contrat ajouterContrat(Contrat contrat);
    void supprimerContrat(long idContrat);
    List<Contrat> recupererContrats();
    Contrat recupererContratById(long idContrat);
    Contrat modifierContrat(long idContrat, Contrat contrat);
}
