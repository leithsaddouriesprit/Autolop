package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Reservation;
import tn.esprit.autoloc.Repositories.ReservationRepo;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationServiceImpl implements IReservation {

    private final ReservationRepo reservationRepo;

    @Override
    @Transactional
    public Reservation ajouterReservation(Reservation reservation) {
        validerReservation(reservation);
        if (reservation.getIdReservation() != null) {
            throw new IllegalArgumentException("L'identifiant doit être absent lors de la création");
        }
        return reservationRepo.save(reservation);
    }

    @Override
    @Transactional
    public void supprimerReservation(long idReservation) {
        if (!reservationRepo.existsById(idReservation)) {
            throw new ResourceNotFoundException("Reservation", idReservation);
        }
        reservationRepo.deleteById(idReservation);
    }

    @Override
    public List<Reservation> recupererReservations() {
        return reservationRepo.findAll();
    }

    @Override
    public Reservation recupererReservationById(long idReservation) {
        return reservationRepo.findById(idReservation)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", idReservation));
    }

    @Override
    @Transactional
    public Reservation updateReservation(Reservation reservation) {
        validerReservation(reservation);
        if (reservation.getIdReservation() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire pour la mise à jour");
        }
        Reservation existant = recupererReservationById(reservation.getIdReservation());
        existant.setDateDebut(reservation.getDateDebut());
        existant.setDateFin(reservation.getDateFin());
        existant.setStatut(reservation.getStatut());
        return reservationRepo.save(existant);
    }

    private void validerReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation obligatoire");
        }
        if (reservation.getDateDebut() == null) {
            throw new IllegalArgumentException("Reservation : dateDebut obligatoire");
        }
        if (reservation.getDateFin() == null) {
            throw new IllegalArgumentException("Reservation : dateFin obligatoire");
        }
        if (reservation.getStatut() == null) {
            throw new IllegalArgumentException("Reservation : statut obligatoire");
        }
        if (reservation.getDateFin().isBefore(reservation.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin ne doit pas précéder la date de début");
        }
    }
}
