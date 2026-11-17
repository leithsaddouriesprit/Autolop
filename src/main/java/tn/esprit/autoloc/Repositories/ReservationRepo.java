package tn.esprit.autoloc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.Reservation;
import tn.esprit.autoloc.Entities.StatutReservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findByStatutAndDateFinBefore(StatutReservation statut, LocalDate date);
}
