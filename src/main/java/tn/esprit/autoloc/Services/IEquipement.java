package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Equipement;

import java.util.List;

public interface IEquipement {
    Equipement ajouterEquipement(Equipement equipement);
    void supprimerEquipement(long idEquipement);
    List<Equipement> recupererEquipements();
    Equipement recupererEquipementById(long idEquipement);
}