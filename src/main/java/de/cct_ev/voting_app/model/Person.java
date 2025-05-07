package de.cct_ev.voting_app.model;

import jakarta.persistence.*;

@Entity             // Markiert die Klasse als Tabelle
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Primärschlüssel
    private Long id;

    @Column(nullable = false, unique = true)
    private String fullName;

    @Column(nullable = false)
    private boolean electable = true;

    // Standard-Konstruktor (JPA braucht ihn)
    public Person() {}

    // Optional: Komfort-Konstruktor
    public Person(String fullName, boolean electable) {
        this.fullName = fullName;
        this.electable = electable;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isElectable() {
        return electable;
    }

    public void setElectable(boolean electable) {
        this.electable = electable;
    }
}
