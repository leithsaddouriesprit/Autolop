package tn.esprit.autoloc.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Services.IAgence;

import java.util.List;

@RestController
@RequestMapping("/api/Agence")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgence iAgence;

    @PostMapping("/AddAgence")
    public Agence ajouterAgence(@RequestBody Agence agence) {
        return iAgence.ajouterAgence(agence);
    }

    @GetMapping("/GetAll")
    public List<Agence> getAllAgences() {
        return iAgence.recupererAgences();
    }

    @PutMapping("/updateAgence")
    public Agence updateAgence(@RequestBody Agence agence) {
        return iAgence.updateAgence(agence);
    }

    @GetMapping("/GetById/{id}")
    public Agence getAgenceById(@PathVariable("id") long id) {
        return iAgence.recupererAgenceById(id);
    }

    @DeleteMapping("/deleteAgence/{id}")
    public void deleteAgence(@PathVariable("id") long id) {
        iAgence.supprimerAgence(id);
    }
}
