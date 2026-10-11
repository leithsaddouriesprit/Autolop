package tn.esprit.autoloc.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Contrat;
import tn.esprit.autoloc.Services.IContrat;

import java.util.List;

@RestController
@RequestMapping("/api/Contrat")
@RequiredArgsConstructor
public class ContratController {

    private final IContrat iContrat;

    @PostMapping("/AddContrat")
    public Contrat ajouterContrat(@RequestBody Contrat contrat) {
        return iContrat.ajouterContrat(contrat);
    }

    @GetMapping("/GetAll")
    public List<Contrat> getAllContrats() {
        return iContrat.recupererContrats();
    }

    @PutMapping("/updateContrat")
    public Contrat updateContrat(@RequestBody Contrat contrat) {
        return iContrat.updateContrat(contrat);
    }

    @GetMapping("/GetById/{id}")
    public Contrat getContratById(@PathVariable("id") long id) {
        return iContrat.recupererContratById(id);
    }

    @DeleteMapping("/deleteContrat/{id}")
    public void deleteContrat(@PathVariable("id") long id) {
        iContrat.supprimerContrat(id);
    }
}
