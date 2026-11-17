package tn.esprit.autoloc.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Services.IEmploye;

import java.util.List;

@RestController
@Tag(name="Gestion Employe")
@RequestMapping("/api/Employe")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmploye iEmploye;

    @Operation(description="ajouter un employé dans la base")
    @PostMapping("/AddEmploye")
    public Employe ajouterEmploye(@RequestBody Employe employe) {
        return iEmploye.ajouterEmploye(employe);
    }

    @Operation(description="récupérer tous les employés")
    @GetMapping("/GetAll")
    public List<Employe> getAllEmployes() {
        return iEmploye.recupererEmployes();
    }

    @Operation(description="modifier un employé existant (idEmploye dans le corps JSON)")
    @PutMapping("/updateEmploye")
    public Employe updateEmploye(@RequestBody Employe employe) {
        return iEmploye.updateEmploye(employe);
    }

    @Operation(description="récupérer un employé par son identifiant")
    @GetMapping("/GetById/{id}")
    public Employe getEmployeById(@PathVariable("id") long id) {
        return iEmploye.recupererEmployeById(id);
    }

    @Operation(description="supprimer un employé par son identifiant")
    @DeleteMapping("/deleteEmploye/{id}")
    public void deleteEmploye(@PathVariable("id") long id) {
        iEmploye.supprimerEmploye(id);
    }
}
