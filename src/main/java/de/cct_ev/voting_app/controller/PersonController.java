package de.cct_ev.voting_app.controller;

import de.cct_ev.voting_app.model.Person;
import de.cct_ev.voting_app.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepo;

    public PersonController(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    /** Alle Personen (wahlbar und nicht wahlbar) zurückgeben */
    @GetMapping
    public List<Person> getAll() {
        return personRepo.findAll();
    }

    /** Neue Person anlegen (standardmäßig wahlbar) */
    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person saved = personRepo.save(person);
        return ResponseEntity.ok(saved);
    }

    /** electable-Flag einer existierenden Person updaten */
    @PatchMapping("/{id}/electable")
    public ResponseEntity<Void> setElectable(
            @PathVariable Long id,
            @RequestParam boolean electable) {
        return personRepo.findById(id)
                .map(p -> {
                    p.setElectable(electable);
                    personRepo.save(p);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
