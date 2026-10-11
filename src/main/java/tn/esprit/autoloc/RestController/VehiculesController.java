package tn.esprit.autoloc.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Vehicules;
import tn.esprit.autoloc.Services.IVehicules;

import java.util.List;

@RestController
@RequestMapping("/api/Vehicule")
@RequiredArgsConstructor
public class VehiculesController {

    private final IVehicules iVehicules;

    @PostMapping("/AddVehicule")
    public Vehicules ajouterVehicule(@RequestBody Vehicules vehicule) {
        return iVehicules.ajouterVehicule(vehicule);
    }

    @GetMapping("/GetAll")
    public List<Vehicules> getAllVehicules() {
        return iVehicules.recupererVehicules();
    }

    @PutMapping("/updateVehicule")
    public Vehicules updateVehicule(@RequestBody Vehicules vehicule) {
        return iVehicules.updateVehicule(vehicule);
    }

    @GetMapping("/GetById/{id}")
    public Vehicules getVehiculeById(@PathVariable("id") long id) {
        return iVehicules.recupererVehiculeById(id);
    }

    @DeleteMapping("/deleteVehicule/{id}")
    public void deleteVehicule(@PathVariable("id") long id) {
        iVehicules.supprimerVehicule(id);
    }
}
