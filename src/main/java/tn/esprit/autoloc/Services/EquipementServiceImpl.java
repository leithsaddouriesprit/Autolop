package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Equipement;
import tn.esprit.autoloc.Repositories.EquipementRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipement {

    private final EquipementRepo equipementRepo;

    @Override
    public Equipement ajouterEquipement(Equipement equipement) {
        return equipementRepo.save(equipement);
    }

    @Override
    public void supprimerEquipement(long idEquipement) {
        equipementRepo.deleteById(idEquipement);
    }

    @Override
    public List<Equipement> recupererEquipements() {
        return equipementRepo.findAll();
    }

    @Override
    public Equipement recupererEquipementById(long idEquipement) {
        return equipementRepo.findById(idEquipement).orElseThrow();
    }
}