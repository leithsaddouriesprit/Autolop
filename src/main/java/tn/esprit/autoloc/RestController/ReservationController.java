package tn.esprit.autoloc.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Reservation;
import tn.esprit.autoloc.Services.IReservation;

import java.util.List;

@RestController
@RequestMapping("/api/Reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservation iReservation;

    @PostMapping("/AddReservation")
    public Reservation ajouterReservation(@RequestBody Reservation reservation) {
        return iReservation.ajouterReservation(reservation);
    }

    @GetMapping("/GetAll")
    public List<Reservation> getAllReservations() {
        return iReservation.recupererReservations();
    }

    @PutMapping("/updateReservation")
    public Reservation updateReservation(@RequestBody Reservation reservation) {
        return iReservation.updateReservation(reservation);
    }

    @GetMapping("/GetById/{id}")
    public Reservation getReservationById(@PathVariable("id") long id) {
        return iReservation.recupererReservationById(id);
    }

    @DeleteMapping("/deleteReservation/{id}")
    public void deleteReservation(@PathVariable("id") long id) {
        iReservation.supprimerReservation(id);
    }
}
