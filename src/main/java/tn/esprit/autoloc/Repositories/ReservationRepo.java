package tn.esprit.autoloc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.Reservation;
@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
}
