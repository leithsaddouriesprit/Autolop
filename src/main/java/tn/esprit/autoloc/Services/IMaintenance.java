package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Maintenance;

import java.util.List;

public interface IMaintenance {
    Maintenance ajouterMaintenance(Maintenance maintenance);
    void supprimerMaintenance(long idMaintenance);
    List<Maintenance> recupererMaintenances();
    Maintenance recupererMaintenanceById(long idMaintenance);
}