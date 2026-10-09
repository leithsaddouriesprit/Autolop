package tn.esprit.autoloc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.Vehicules;

@Repository
public interface VehiculesRepo extends JpaRepository<Vehicules, Long> {
}
