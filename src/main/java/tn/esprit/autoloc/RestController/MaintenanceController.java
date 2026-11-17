package tn.esprit.autoloc.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Maintenance;
import tn.esprit.autoloc.Services.IMaintenance;

import java.util.List;

@RestController
@Tag(name="Gestion Maintenance")
@RequestMapping("/api/Maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenance iMaintenance;

    @Operation(description="ajouter une maintenance dans la base")
    @PostMapping("/AddMaintenance")
    public Maintenance ajouterMaintenance(@RequestBody Maintenance maintenance) {
        return iMaintenance.ajouterMaintenance(maintenance);
    }

    @Operation(description="récupérer toutes les maintenances")
    @GetMapping("/GetAll")
    public List<Maintenance> getAllMaintenances() {
        return iMaintenance.recupererMaintenances();
    }

    @Operation(description="modifier une maintenance existante (idMaintenance dans le corps JSON)")
    @PutMapping("/updateMaintenance")
    public Maintenance updateMaintenance(@RequestBody Maintenance maintenance) {
        return iMaintenance.updateMaintenance(maintenance);
    }

    @Operation(description="récupérer une maintenance par son identifiant")
    @GetMapping("/GetById/{id}")
    public Maintenance getMaintenanceById(@PathVariable("id") long id) {
        return iMaintenance.recupererMaintenanceById(id);
    }

    @Operation(description="supprimer une maintenance par son identifiant")
    @DeleteMapping("/deleteMaintenance/{id}")
    public void deleteMaintenance(@PathVariable("id") long id) {
        iMaintenance.supprimerMaintenance(id);
    }
}
