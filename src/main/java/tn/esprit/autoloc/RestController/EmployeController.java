package tn.esprit.autoloc.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Services.IEmploye;

import java.util.List;

@RestController
@RequestMapping("/api/Employe")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmploye iEmploye;

    @PostMapping("/AddEmploye")
    public Employe ajouterEmploye(@RequestBody Employe employe) {
        return iEmploye.ajouterEmploye(employe);
    }

    @GetMapping("/GetAll")
    public List<Employe> getAllEmployes() {
        return iEmploye.recupererEmployes();
    }

    @PutMapping("/updateEmploye")
    public Employe updateEmploye(@RequestBody Employe employe) {
        return iEmploye.updateEmploye(employe);
    }

    @GetMapping("/GetById/{id}")
    public Employe getEmployeById(@PathVariable("id") long id) {
        return iEmploye.recupererEmployeById(id);
    }

    @DeleteMapping("/deleteEmploye/{id}")
    public void deleteEmploye(@PathVariable("id") long id) {
        iEmploye.supprimerEmploye(id);
    }
}
