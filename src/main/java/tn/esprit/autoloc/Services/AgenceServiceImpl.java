package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Repositories.AgenceRepo;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AgenceServiceImpl implements IAgence {

    private final AgenceRepo agenceRepo;

    @Override
    @Transactional
    public Agence ajouterAgence(Agence agence) {
        validerAgence(agence);
        if (agence.getIdAgence() != null) {
            throw new IllegalArgumentException("L'identifiant doit être absent lors de la création");
        }
        return agenceRepo.save(agence);
    }

    @Override
    @Transactional
    public void supprimerAgence(long idAgence) {
        if (!agenceRepo.existsById(idAgence)) {
            throw new ResourceNotFoundException("Agence", idAgence);
        }
        agenceRepo.deleteById(idAgence);
    }

    @Override
    public List<Agence> recupererAgences() {
        return agenceRepo.findAll();
    }

    @Override
    public Agence recupererAgenceById(long idAgence) {
        return agenceRepo.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", idAgence));
    }

    @Override
    @Transactional
    public Agence updateAgence(Agence agence) {
        validerAgence(agence);
        if (agence.getIdAgence() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire pour la mise à jour");
        }
        Agence existant = recupererAgenceById(agence.getIdAgence());
        existant.setNom(agence.getNom());
        existant.setVille(agence.getVille());
        existant.setAdresse(agence.getAdresse());
        existant.setTelephone(agence.getTelephone());
        return agenceRepo.save(existant);
    }

    private void validerAgence(Agence agence) {
        if (agence == null) {
            throw new IllegalArgumentException("Agence obligatoire");
        }
        if (agence.getNom() == null || agence.getNom().isBlank()) {
            throw new IllegalArgumentException("Agence : nom obligatoire");
        }
        if (agence.getVille() == null || agence.getVille().isBlank()) {
            throw new IllegalArgumentException("Agence : ville obligatoire");
        }
        if (agence.getAdresse() == null || agence.getAdresse().isBlank()) {
            throw new IllegalArgumentException("Agence : adresse obligatoire");
        }
        if (agence.getTelephone() == null || agence.getTelephone().isBlank()) {
            throw new IllegalArgumentException("Agence : telephone obligatoire");
        }
    }
}
