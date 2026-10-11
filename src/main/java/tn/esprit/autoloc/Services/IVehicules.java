package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Vehicules;

import java.util.List;

public interface IVehicules {
    Vehicules ajouterVehicule(Vehicules vehicule);
    void supprimerVehicule(long idVehicule);
    List<Vehicules> recupererVehicules();
    Vehicules recupererVehiculeById(long idVehicule);
    Vehicules updateVehicule(Vehicules vehicule);
}
