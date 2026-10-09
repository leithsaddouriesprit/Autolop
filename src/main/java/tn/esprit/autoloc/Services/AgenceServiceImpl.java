package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Repositories.AgenceRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgence {

    private final AgenceRepo agenceRepo;

    @Override
    public Agence ajouterAgence(Agence agence) {
        return agenceRepo.save(agence);
    }

    @Override
    public void supprimerAgence(long idAgence) {
        agenceRepo.deleteById(idAgence);
    }

    @Override
    public List<Agence> recupererAgences() {
        return agenceRepo.findAll();
    }

    @Override
    public Agence recupererAgenceById(long idAgence) {
        return agenceRepo.findById(idAgence).orElseThrow();
    }
}