package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Employe;

import java.util.List;

public interface IEmploye {
    Employe ajouterEmploye(Employe employe);
    void supprimerEmploye(long idEmploye);
    List<Employe> recupererEmployes();
    Employe recupererEmployeById(long idEmploye);
    Employe modifierEmploye(long idEmploye, Employe employe);
}
