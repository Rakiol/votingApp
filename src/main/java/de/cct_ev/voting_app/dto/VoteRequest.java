package de.cct_ev.voting_app.dto;

public class VoteRequest {
    private Long personId;

    public VoteRequest() {}

    public VoteRequest(Long personId) {
        this.personId = personId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
