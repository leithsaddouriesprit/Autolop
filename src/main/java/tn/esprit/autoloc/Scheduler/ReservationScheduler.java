package tn.esprit.autoloc.Scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.Services.IReservation;

@Component
@RequiredArgsConstructor
public class ReservationScheduler {

    private final IReservation iReservation;

    @Scheduled(fixedRate = 60000)
    public void verifierReservations() {
        iReservation.terminerReservationsExpirees();
    }
}
