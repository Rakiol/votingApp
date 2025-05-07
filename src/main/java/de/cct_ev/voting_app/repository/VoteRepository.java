package de.cct_ev.voting_app.repository;

import de.cct_ev.voting_app.model.Vote;
import de.cct_ev.voting_app.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    // prüfen, ob der User schon gewählt hat
    boolean existsByVoter(AppUser voter);

    // Zählt Stimmen pro Kandidat, liefert Name und Count
    @Query("""
    SELECT v.candidate.fullName AS name, COUNT(v) AS cnt
    FROM Vote v
    GROUP BY v.candidate.fullName
  """)
    List<CandidateCount> countVotesByCandidate();

    // Projection-Interface für obige Query
    interface CandidateCount {
        String getName();
        long getCnt();
    }
}
