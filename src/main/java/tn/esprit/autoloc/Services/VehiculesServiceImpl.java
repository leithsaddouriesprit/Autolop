package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Vehicules;
import tn.esprit.autoloc.Repositories.VehiculesRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculesServiceImpl implements IVehicules {

    private final VehiculesRepo vehiculesRepo;

    @Override
    public Vehicules ajouterVehicule(Vehicules vehicule) {
        return vehiculesRepo.save(vehicule);
    }

    @Override
    public void supprimerVehicule(long idVehicule) {
        vehiculesRepo.deleteById(idVehicule);
    }

    @Override
    public List<Vehicules> recupererVehicules() {
        return vehiculesRepo.findAll();
    }

    @Override
    public Vehicules recupererVehiculeById(long idVehicule) {
        return vehiculesRepo.findById(idVehicule).orElseThrow();
    }
}