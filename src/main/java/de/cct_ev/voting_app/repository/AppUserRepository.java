package de.cct_ev.voting_app.repository;

import de.cct_ev.voting_app.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    // zum Login später: Nutzer nach Username laden
    Optional<AppUser> findByUsername(String username);
}
