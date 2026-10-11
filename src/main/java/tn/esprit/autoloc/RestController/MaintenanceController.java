package tn.esprit.autoloc.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Maintenance;
import tn.esprit.autoloc.Services.IMaintenance;

import java.util.List;

@RestController
@RequestMapping("/api/Maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenance iMaintenance;

    @PostMapping("/AddMaintenance")
    public Maintenance ajouterMaintenance(@RequestBody Maintenance maintenance) {
        return iMaintenance.ajouterMaintenance(maintenance);
    }

    @GetMapping("/GetAll")
    public List<Maintenance> getAllMaintenances() {
        return iMaintenance.recupererMaintenances();
    }

    @PutMapping("/updateMaintenance")
    public Maintenance updateMaintenance(@RequestBody Maintenance maintenance) {
        return iMaintenance.updateMaintenance(maintenance);
    }

    @GetMapping("/GetById/{id}")
    public Maintenance getMaintenanceById(@PathVariable("id") long id) {
        return iMaintenance.recupererMaintenanceById(id);
    }

    @DeleteMapping("/deleteMaintenance/{id}")
    public void deleteMaintenance(@PathVariable("id") long id) {
        iMaintenance.supprimerMaintenance(id);
    }
}
