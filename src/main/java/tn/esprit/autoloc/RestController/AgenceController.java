package tn.esprit.autoloc.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Services.IAgence;

import java.util.List;

@RestController
@Tag(name="Gestion Agence")
@RequestMapping("/api/Agence")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgence iAgence;

    @Operation(description="ajouter une agence dans la base")
    @PostMapping("/AddAgence")
    public Agence ajouterAgence(@RequestBody Agence agence) {
        return iAgence.ajouterAgence(agence);
    }

    @Operation(description="récupérer toutes les agences")
    @GetMapping("/GetAll")
    public List<Agence> getAllAgences() {
        return iAgence.recupererAgences();
    }

    @Operation(description="modifier une agence existante (idAgence dans le corps JSON)")
    @PutMapping("/updateAgence")
    public Agence updateAgence(@RequestBody Agence agence) {
        return iAgence.updateAgence(agence);
    }

    @Operation(description="récupérer une agence par son identifiant")
    @GetMapping("/GetById/{id}")
    public Agence getAgenceById(@PathVariable("id") long id) {
        return iAgence.recupererAgenceById(id);
    }

    @Operation(description="supprimer une agence par son identifiant")
    @DeleteMapping("/deleteAgence/{id}")
    public void deleteAgence(@PathVariable("id") long id) {
        iAgence.supprimerAgence(id);
    }
}
