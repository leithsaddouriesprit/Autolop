package tn.esprit.autoloc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.Vehicule;

@Repository
public interface VehiculeRepo extends JpaRepository<Vehicule, Long> {
}
