package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Maintenance;
import tn.esprit.autoloc.Repositories.MaintenanceRepo;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MaintenanceServiceImpl implements IMaintenance {

    private final MaintenanceRepo maintenanceRepo;

    @Override
    @Transactional
    public Maintenance ajouterMaintenance(Maintenance maintenance) {
        validerMaintenance(maintenance);
        if (maintenance.getIdMaintenance() != null) {
            throw new IllegalArgumentException("L'identifiant doit être absent lors de la création");
        }
        return maintenanceRepo.save(maintenance);
    }

    @Override
    @Transactional
    public void supprimerMaintenance(long idMaintenance) {
        if (!maintenanceRepo.existsById(idMaintenance)) {
            throw new ResourceNotFoundException("Maintenance", idMaintenance);
        }
        maintenanceRepo.deleteById(idMaintenance);
    }

    @Override
    public List<Maintenance> recupererMaintenances() {
        return maintenanceRepo.findAll();
    }

    @Override
    public Maintenance recupererMaintenanceById(long idMaintenance) {
        return maintenanceRepo.findById(idMaintenance)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", idMaintenance));
    }

    @Override
    @Transactional
    public Maintenance modifierMaintenance(long idMaintenance, Maintenance maintenance) {
        Maintenance existant = recupererMaintenanceById(idMaintenance);
        validerMaintenance(maintenance);
        existant.setDateDebut(maintenance.getDateDebut());
        existant.setDateFin(maintenance.getDateFin());
        existant.setDescription(maintenance.getDescription());
        return maintenanceRepo.save(existant);
    }

    private void validerMaintenance(Maintenance maintenance) {
        if (maintenance == null) {
            throw new IllegalArgumentException("Maintenance obligatoire");
        }
        if (maintenance.getDescription() == null || maintenance.getDescription().isBlank()) {
            throw new IllegalArgumentException("Maintenance : description obligatoire");
        }
        if (maintenance.getDateDebut() == null) {
            throw new IllegalArgumentException("Maintenance : dateDebut obligatoire");
        }
        if (maintenance.getDateFin() == null) {
            throw new IllegalArgumentException("Maintenance : dateFin obligatoire");
        }
        if (maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin ne doit pas précéder la date de début");
        }
    }
}
