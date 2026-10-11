package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Repositories.EmployeRepo;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeServiceImpl implements IEmploye {

    private final EmployeRepo employeRepo;

    @Override
    @Transactional
    public Employe ajouterEmploye(Employe employe) {
        validerEmploye(employe);
        if (employe.getIdEmploye() != null) {
            throw new IllegalArgumentException("L'identifiant doit être absent lors de la création");
        }
        return employeRepo.save(employe);
    }

    @Override
    @Transactional
    public void supprimerEmploye(long idEmploye) {
        if (!employeRepo.existsById(idEmploye)) {
            throw new ResourceNotFoundException("Employe", idEmploye);
        }
        employeRepo.deleteById(idEmploye);
    }

    @Override
    public List<Employe> recupererEmployes() {
        return employeRepo.findAll();
    }

    @Override
    public Employe recupererEmployeById(long idEmploye) {
        return employeRepo.findById(idEmploye)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", idEmploye));
    }

    @Override
    @Transactional
    public Employe updateEmploye(Employe employe) {
        validerEmploye(employe);
        if (employe.getIdEmploye() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire pour la mise à jour");
        }
        Employe existant = recupererEmployeById(employe.getIdEmploye());
        existant.setNom(employe.getNom());
        existant.setPrenom(employe.getPrenom());
        existant.setRole(employe.getRole());
        return employeRepo.save(existant);
    }

    private void validerEmploye(Employe employe) {
        if (employe == null) {
            throw new IllegalArgumentException("Employe obligatoire");
        }
        if (employe.getNom() == null || employe.getNom().isBlank()) {
            throw new IllegalArgumentException("Employe : nom obligatoire");
        }
        if (employe.getPrenom() == null || employe.getPrenom().isBlank()) {
            throw new IllegalArgumentException("Employe : prenom obligatoire");
        }
        if (employe.getRole() == null) {
            throw new IllegalArgumentException("Employe : role obligatoire");
        }
    }
}
