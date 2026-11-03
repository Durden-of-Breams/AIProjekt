package org.example.aiprojekttest.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Roast> roasts = new ArrayList<>();

    // Mangler password (hashed spring security)

    // Skal nok ændres depending af hvad vi vil bruge det til og hvor vidt man starter med roasts.
    public User(String username) {
        this.username = username;
    }

    public User() {}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void addRoast(Roast roast) {
        roasts.add(roast);
    }

    public List<Roast> getRoasts() {
        return roasts;
    }
}
