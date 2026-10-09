package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Contrat;
import tn.esprit.autoloc.Repositories.ContratRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContrat {

    private final ContratRepo contratRepo;

    @Override
    public Contrat ajouterContrat(Contrat contrat) {
        return contratRepo.save(contrat);
    }

    @Override
    public void supprimerContrat(long idContrat) {
        contratRepo.deleteById(idContrat);
    }

    @Override
    public List<Contrat> recupererContrats() {
        return contratRepo.findAll();
    }

    @Override
    public Contrat recupererContratById(long idContrat) {
        return contratRepo.findById(idContrat).orElseThrow();
    }
}