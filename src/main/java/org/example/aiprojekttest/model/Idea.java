package org.example.aiprojekttest.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String idea;

    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Roast> roasts = new ArrayList<>();

    public Idea() {}

    public Idea(String idea) {
        this.idea = idea;
    }

    public void addRoast(Roast roast) {
        roasts.add(roast);
        roast.setIdea(this);
    }

    public void removeRoast(Roast roast) {
        roasts.remove(roast);
        roast.setIdea(null);
    }

    // getters / setters

    public Long getId() {
        return id;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public List<Roast> getRoasts() {
        return roasts;
    }

    public void setRoasts(List<Roast> roasts) {
        this.roasts = roasts;
    }
}