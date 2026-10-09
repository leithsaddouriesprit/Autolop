package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Repositories.EmployeRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmploye {

    private final EmployeRepo employeRepo;

    @Override
    public Employe ajouterEmploye(Employe employe) {
        return employeRepo.save(employe);
    }

    @Override
    public void supprimerEmploye(long idEmploye) {
        employeRepo.deleteById(idEmploye);
    }

    @Override
    public List<Employe> recupererEmployes() {
        return employeRepo.findAll();
    }

    @Override
    public Employe recupererEmployeById(long idEmploye) {
        return employeRepo.findById(idEmploye).orElseThrow();
    }
}