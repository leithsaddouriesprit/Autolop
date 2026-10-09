package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Reservation;

import java.util.List;

public interface IReservation {
    Reservation ajouterReservation(Reservation reservation);
    void supprimerReservation(long idReservation);
    List<Reservation> recupererReservations();
    Reservation recupererReservationById(long idReservation);
}