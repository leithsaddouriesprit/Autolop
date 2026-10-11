package tn.esprit.autoloc.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Paiement;
import tn.esprit.autoloc.Services.IPaiement;

import java.util.List;

@RestController
@RequestMapping("/api/Paiement")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiement iPaiement;

    @GetMapping("/GetAll")
    public List<Paiement> getAllPaiements() {
        return iPaiement.recupererPaiements();
    }

    @GetMapping("/GetById/{id}")
    public Paiement getPaiementById(@PathVariable("id") long id) {
        return iPaiement.recupererPaiementById(id);
    }
}
