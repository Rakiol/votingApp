package de.cct_ev.voting_app.repository;

import de.cct_ev.voting_app.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    // findet nur wählbare Personen
    Optional<Person> findByIdAndElectableTrue(Long id);
}
