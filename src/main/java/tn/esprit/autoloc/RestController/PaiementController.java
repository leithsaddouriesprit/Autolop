package tn.esprit.autoloc.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Paiement;
import tn.esprit.autoloc.Services.IPaiement;

import java.util.List;

@RestController
@Tag(name="Gestion Paiement")
@RequestMapping("/api/Paiement")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiement iPaiement;

    @Operation(description="récupérer tous les paiements (ils sont créés avec leur contrat)")
    @GetMapping("/GetAll")
    public List<Paiement> getAllPaiements() {
        return iPaiement.recupererPaiements();
    }

    @Operation(description="récupérer un paiement par son identifiant")
    @GetMapping("/GetById/{id}")
    public Paiement getPaiementById(@PathVariable("id") long id) {
        return iPaiement.recupererPaiementById(id);
    }
}
