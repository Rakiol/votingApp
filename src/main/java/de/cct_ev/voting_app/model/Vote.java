package de.cct_ev.voting_app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private AppUser voter;

    @ManyToOne(optional = false)
    private Person candidate;

    @Column(nullable = false)
    private LocalDateTime timestamp;       // für die Stimmabgabe aktueles Datum

    public Vote() {}

    public Vote(AppUser voter, Person candidate, LocalDateTime timestamp) {
        this.voter = voter;
        this.candidate = candidate;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public AppUser getVoter() {
        return voter;
    }

    public void setVoter(AppUser voter) {
        this.voter = voter;
    }

    public Person getCandidate() {
        return candidate;
    }

    public void setCandidate(Person candidate) {
        this.candidate = candidate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

