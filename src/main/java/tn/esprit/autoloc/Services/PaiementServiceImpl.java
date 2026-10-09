package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Paiement;
import tn.esprit.autoloc.Repositories.PaiementRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiement {

    private final PaiementRepo paiementRepo;

    @Override
    public List<Paiement> recupererPaiements() {
        return paiementRepo.findAll();
    }

    @Override
    public Paiement recupererPaiementById(long idPaiement) {
        return paiementRepo.findById(idPaiement).orElseThrow();
    }
}