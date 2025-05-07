package de.cct_ev.voting_app.service;

import de.cct_ev.voting_app.dto.ResultDto;
import de.cct_ev.voting_app.model.Person;
import de.cct_ev.voting_app.model.Vote;
import de.cct_ev.voting_app.model.AppUser;
import de.cct_ev.voting_app.repository.PersonRepository;
import de.cct_ev.voting_app.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VotingService {

    private final PersonRepository personRepo;
    private final VoteRepository voteRepo;

    public VotingService(PersonRepository personRepo, VoteRepository voteRepo) {
        this.personRepo = personRepo;
        this.voteRepo = voteRepo;
    }

    /**
     * Gibt eine Stimme ab.
     * @throws IllegalStateException wenn schon gewählt wurde
     */
    @Transactional    //Wichtig um bei einem Fehler die DB-Änderung zurückzusetzten
    public void castVote(AppUser voter, Long candidateId) {
        // 1. nur eine Stimme pro User
        if (voteRepo.existsByVoter(voter)) {
            throw new IllegalStateException("Nur eine Stimme pro Nutzer erlaubt");
        }
        // 2. Kandidat muss existieren und wählbar sein
        Person candidate = personRepo
                .findByIdAndElectableTrue(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Ungültiger Kandidat"));
        // 3. Stimme speichern
        Vote vote = new Vote(voter, candidate, LocalDateTime.now());
        voteRepo.save(vote);
    }

    /**
     * Liefert pro Kandidat den Prozent-Anteil der Stimmen.
     */
    public List<ResultDto> getResults() {
        long totalVotes = voteRepo.count();
        return voteRepo.countVotesByCandidate()
                .stream()
                .map(cc -> new ResultDto(
                        cc.getName(),
                        totalVotes == 0 ? 0.0 : (100.0 * cc.getCnt() / totalVotes)
                ))
                .toList();
    }
}
