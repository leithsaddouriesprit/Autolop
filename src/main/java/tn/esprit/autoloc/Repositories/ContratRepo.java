package tn.esprit.autoloc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.Contrat;
@Repository
public interface ContratRepo extends JpaRepository<Contrat, Long> {
}
