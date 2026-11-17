package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Vehicule;

import java.util.List;

public interface IVehicule {
    Vehicule ajouterVehicule(Vehicule vehicule);
    void supprimerVehicule(long idVehicule);
    List<Vehicule> recupererVehicules();
    Vehicule recupererVehiculeById(long idVehicule);
    Vehicule updateVehicule(Vehicule vehicule);
}
