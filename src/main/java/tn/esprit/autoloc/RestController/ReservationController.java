package tn.esprit.autoloc.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Reservation;
import tn.esprit.autoloc.Services.IReservation;

import java.util.List;

@RestController
@Tag(name="Gestion Reservation")
@RequestMapping("/api/Reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservation iReservation;

    @Operation(description="ajouter une réservation dans la base, avec son contrat et ses paiements éventuels")
    @PostMapping("/AddReservation")
    public Reservation ajouterReservation(@RequestBody Reservation reservation) {
        return iReservation.ajouterReservation(reservation);
    }

    @Operation(description="récupérer toutes les réservations")
    @GetMapping("/GetAll")
    public List<Reservation> getAllReservations() {
        return iReservation.recupererReservations();
    }

    @Operation(description="modifier une réservation existante (idReservation dans le corps JSON)")
    @PutMapping("/updateReservation")
    public Reservation updateReservation(@RequestBody Reservation reservation) {
        return iReservation.updateReservation(reservation);
    }

    @Operation(description="récupérer une réservation par son identifiant")
    @GetMapping("/GetById/{id}")
    public Reservation getReservationById(@PathVariable("id") long id) {
        return iReservation.recupererReservationById(id);
    }

    @Operation(description="supprimer une réservation par son identifiant")
    @DeleteMapping("/deleteReservation/{id}")
    public void deleteReservation(@PathVariable("id") long id) {
        iReservation.supprimerReservation(id);
    }
}
