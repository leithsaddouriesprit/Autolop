package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Contrat;
import tn.esprit.autoloc.Repositories.ContratRepo;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContratServiceImpl implements IContrat {

    private final ContratRepo contratRepo;

    @Override
    @Transactional
    public Contrat ajouterContrat(Contrat contrat) {
        validerContrat(contrat);
        if (contrat.getIdContrat() != null) {
            throw new IllegalArgumentException("L'identifiant doit être absent lors de la création");
        }
        return contratRepo.save(contrat);
    }

    @Override
    @Transactional
    public void supprimerContrat(long idContrat) {
        if (!contratRepo.existsById(idContrat)) {
            throw new ResourceNotFoundException("Contrat", idContrat);
        }
        contratRepo.deleteById(idContrat);
    }

    @Override
    public List<Contrat> recupererContrats() {
        return contratRepo.findAll();
    }

    @Override
    public Contrat recupererContratById(long idContrat) {
        return contratRepo.findById(idContrat)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", idContrat));
    }

    @Override
    @Transactional
    public Contrat updateContrat(Contrat contrat) {
        validerContrat(contrat);
        if (contrat.getIdContrat() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire pour la mise à jour");
        }
        Contrat existant = recupererContratById(contrat.getIdContrat());
        existant.setDateSignature(contrat.getDateSignature());
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setValide(contrat.isValide());
        return contratRepo.save(existant);
    }

    private void validerContrat(Contrat contrat) {
        if (contrat == null) {
            throw new IllegalArgumentException("Contrat obligatoire");
        }
        if (contrat.getDateSignature() == null) {
            throw new IllegalArgumentException("Contrat : dateSignature obligatoire");
        }
        if (contrat.getMontantTotal() == null) {
            throw new IllegalArgumentException("Contrat : montantTotal obligatoire");
        }
        if (contrat.getMontantTotal().signum() < 0) {
            throw new IllegalArgumentException("Contrat : montantTotal ne doit pas être négatif");
        }
    }
}
