package tn.esprit.autoloc.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Contrat;
import tn.esprit.autoloc.Services.IContrat;

import java.util.List;

@RestController
@Tag(name="Gestion Contrat")
@RequestMapping("/api/Contrat")
@RequiredArgsConstructor
public class ContratController {

    private final IContrat iContrat;

    @Operation(description="ajouter un contrat dans la base, avec ses paiements éventuels")
    @PostMapping("/AddContrat")
    public Contrat ajouterContrat(@RequestBody Contrat contrat) {
        return iContrat.ajouterContrat(contrat);
    }

    @Operation(description="récupérer tous les contrats")
    @GetMapping("/GetAll")
    public List<Contrat> getAllContrats() {
        return iContrat.recupererContrats();
    }

    @Operation(description="modifier un contrat existant (idContrat dans le corps JSON)")
    @PutMapping("/updateContrat")
    public Contrat updateContrat(@RequestBody Contrat contrat) {
        return iContrat.updateContrat(contrat);
    }

    @Operation(description="récupérer un contrat par son identifiant")
    @GetMapping("/GetById/{id}")
    public Contrat getContratById(@PathVariable("id") long id) {
        return iContrat.recupererContratById(id);
    }

    @Operation(description="supprimer un contrat par son identifiant")
    @DeleteMapping("/deleteContrat/{id}")
    public void deleteContrat(@PathVariable("id") long id) {
        iContrat.supprimerContrat(id);
    }
}
