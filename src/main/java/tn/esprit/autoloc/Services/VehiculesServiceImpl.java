package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Vehicules;
import tn.esprit.autoloc.Repositories.VehiculesRepo;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VehiculesServiceImpl implements IVehicules {

    private final VehiculesRepo vehiculesRepo;

    @Override
    @Transactional
    public Vehicules ajouterVehicule(Vehicules vehicule) {
        validerVehicule(vehicule);
        if (vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException("L'identifiant doit être absent lors de la création");
        }
        return vehiculesRepo.save(vehicule);
    }

    @Override
    @Transactional
    public void supprimerVehicule(long idVehicule) {
        if (!vehiculesRepo.existsById(idVehicule)) {
            throw new ResourceNotFoundException("Vehicule", idVehicule);
        }
        vehiculesRepo.deleteById(idVehicule);
    }

    @Override
    public List<Vehicules> recupererVehicules() {
        return vehiculesRepo.findAll();
    }

    @Override
    public Vehicules recupererVehiculeById(long idVehicule) {
        return vehiculesRepo.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", idVehicule));
    }

    @Override
    @Transactional
    public Vehicules modifierVehicule(long idVehicule, Vehicules vehicule) {
        Vehicules existant = recupererVehiculeById(idVehicule);
        validerVehicule(vehicule);
        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());
        return vehiculesRepo.save(existant);
    }

    private void validerVehicule(Vehicules vehicule) {
        if (vehicule == null) {
            throw new IllegalArgumentException("Vehicule obligatoire");
        }
        if (vehicule.getImmatriculation() == null || vehicule.getImmatriculation().isBlank()) {
            throw new IllegalArgumentException("Vehicule : immatriculation obligatoire");
        }
        if (vehicule.getMarque() == null || vehicule.getMarque().isBlank()) {
            throw new IllegalArgumentException("Vehicule : marque obligatoire");
        }
        if (vehicule.getModele() == null || vehicule.getModele().isBlank()) {
            throw new IllegalArgumentException("Vehicule : modele obligatoire");
        }
        if (vehicule.getCategorie() == null) {
            throw new IllegalArgumentException("Vehicule : categorie obligatoire");
        }
        if (vehicule.getTarifJournalier() == null) {
            throw new IllegalArgumentException("Vehicule : tarifJournalier obligatoire");
        }
        if (vehicule.getStatut() == null) {
            throw new IllegalArgumentException("Vehicule : statut obligatoire");
        }
        if (vehicule.getTarifJournalier().signum() < 0) {
            throw new IllegalArgumentException("Vehicule : tarifJournalier ne doit pas être négatif");
        }
    }
}
