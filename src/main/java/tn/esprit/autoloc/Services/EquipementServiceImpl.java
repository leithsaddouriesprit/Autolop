package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Equipement;
import tn.esprit.autoloc.Repositories.EquipementRepo;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EquipementServiceImpl implements IEquipement {

    private final EquipementRepo equipementRepo;

    @Override
    @Transactional
    public Equipement ajouterEquipement(Equipement equipement) {
        validerEquipement(equipement);
        if (equipement.getIdEquipement() != null) {
            throw new IllegalArgumentException("L'identifiant doit être absent lors de la création");
        }
        return equipementRepo.save(equipement);
    }

    @Override
    @Transactional
    public void supprimerEquipement(long idEquipement) {
        if (!equipementRepo.existsById(idEquipement)) {
            throw new ResourceNotFoundException("Equipement", idEquipement);
        }
        equipementRepo.deleteById(idEquipement);
    }

    @Override
    public List<Equipement> recupererEquipements() {
        return equipementRepo.findAll();
    }

    @Override
    public Equipement recupererEquipementById(long idEquipement) {
        return equipementRepo.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", idEquipement));
    }

    @Override
    @Transactional
    public Equipement modifierEquipement(long idEquipement, Equipement equipement) {
        Equipement existant = recupererEquipementById(idEquipement);
        validerEquipement(equipement);
        existant.setLibelle(equipement.getLibelle());
        return equipementRepo.save(existant);
    }

    private void validerEquipement(Equipement equipement) {
        if (equipement == null) {
            throw new IllegalArgumentException("Equipement obligatoire");
        }
        if (equipement.getLibelle() == null || equipement.getLibelle().isBlank()) {
            throw new IllegalArgumentException("Equipement : libelle obligatoire");
        }
    }
}
