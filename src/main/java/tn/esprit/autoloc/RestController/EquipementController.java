package tn.esprit.autoloc.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Equipement;
import tn.esprit.autoloc.Services.IEquipement;

import java.util.List;

@RestController
@RequestMapping("/api/Equipement")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipement iEquipement;

    @PostMapping("/AddEquipement")
    public Equipement ajouterEquipement(@RequestBody Equipement equipement) {
        return iEquipement.ajouterEquipement(equipement);
    }

    @GetMapping("/GetAll")
    public List<Equipement> getAllEquipements() {
        return iEquipement.recupererEquipements();
    }

    @PutMapping("/updateEquipement")
    public Equipement updateEquipement(@RequestBody Equipement equipement) {
        return iEquipement.updateEquipement(equipement);
    }

    @GetMapping("/GetById/{id}")
    public Equipement getEquipementById(@PathVariable("id") long id) {
        return iEquipement.recupererEquipementById(id);
    }

    @DeleteMapping("/deleteEquipement/{id}")
    public void deleteEquipement(@PathVariable("id") long id) {
        iEquipement.supprimerEquipement(id);
    }
}
