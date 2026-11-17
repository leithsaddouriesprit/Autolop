package tn.esprit.autoloc.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Vehicule;
import tn.esprit.autoloc.Services.IVehicule;

import java.util.List;

@RestController
@Tag(name="Gestion Vehicule")
@RequestMapping("/api/Vehicule")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehicule iVehicule;

    @Operation(description="ajouter un véhicule dans la base")
    @PostMapping("/AddVehicule")
    public Vehicule ajouterVehicule(@RequestBody Vehicule vehicule) {
        return iVehicule.ajouterVehicule(vehicule);
    }

    @Operation(description="récupérer tous les véhicules")
    @GetMapping("/GetAll")
    public List<Vehicule> getAllVehicules() {
        return iVehicule.recupererVehicules();
    }

    @Operation(description="modifier un véhicule existant (idVehicule dans le corps JSON)")
    @PutMapping("/updateVehicule")
    public Vehicule updateVehicule(@RequestBody Vehicule vehicule) {
        return iVehicule.updateVehicule(vehicule);
    }

    @Operation(description="récupérer un véhicule par son identifiant")
    @GetMapping("/GetById/{id}")
    public Vehicule getVehiculeById(@PathVariable("id") long id) {
        return iVehicule.recupererVehiculeById(id);
    }

    @Operation(description="supprimer un véhicule par son identifiant")
    @DeleteMapping("/deleteVehicule/{id}")
    public void deleteVehicule(@PathVariable("id") long id) {
        iVehicule.supprimerVehicule(id);
    }
}
