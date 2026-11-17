package tn.esprit.autoloc.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Equipement;
import tn.esprit.autoloc.Services.IEquipement;

import java.util.List;

@RestController
@Tag(name="Gestion Equipement")
@RequestMapping("/api/Equipement")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipement iEquipement;

    @Operation(description="ajouter un équipement dans la base")
    @PostMapping("/AddEquipement")
    public Equipement ajouterEquipement(@RequestBody Equipement equipement) {
        return iEquipement.ajouterEquipement(equipement);
    }

    @Operation(description="récupérer tous les équipements")
    @GetMapping("/GetAll")
    public List<Equipement> getAllEquipements() {
        return iEquipement.recupererEquipements();
    }

    @Operation(description="modifier un équipement existant (idEquipement dans le corps JSON)")
    @PutMapping("/updateEquipement")
    public Equipement updateEquipement(@RequestBody Equipement equipement) {
        return iEquipement.updateEquipement(equipement);
    }

    @Operation(description="récupérer un équipement par son identifiant")
    @GetMapping("/GetById/{id}")
    public Equipement getEquipementById(@PathVariable("id") long id) {
        return iEquipement.recupererEquipementById(id);
    }

    @Operation(description="supprimer un équipement par son identifiant")
    @DeleteMapping("/deleteEquipement/{id}")
    public void deleteEquipement(@PathVariable("id") long id) {
        iEquipement.supprimerEquipement(id);
    }
}
