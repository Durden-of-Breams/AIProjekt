package org.example.aiprojekttest.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Idea> ideas = new ArrayList<>();

    // Mangler password (hashed spring security)

    // Skal nok ændres depending af hvad vi vil bruge det til og hvor vidt man starter med roasts.
    public AppUser(String username) {
        this.username = username;
    }

    public void addIdea(Idea idea) {
        ideas.add(idea);
        idea.setUser(this);
    }

    public AppUser() {}

    public Long getId() {
        return id;
    }

    public List<Idea> getIdeas() {
        return ideas;
    }

    public void removeIdea(Idea idea) {
        ideas.remove(idea);
        idea.setUser(null);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
