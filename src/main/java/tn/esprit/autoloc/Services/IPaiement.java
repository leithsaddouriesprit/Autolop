package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Paiement;

import java.util.List;

public interface IPaiement {
    List<Paiement> recupererPaiements();
    Paiement recupererPaiementById(long idPaiement);
}
