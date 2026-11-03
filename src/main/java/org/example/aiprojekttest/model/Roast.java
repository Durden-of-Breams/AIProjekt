package org.example.aiprojekttest.model;

import jakarta.persistence.*;

@Entity
public class Roast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roast;

    private double roastScore;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    public Roast() {}

    public Roast(Long id, String roast, double roastScore, Idea idea) {
        this.id = id;
        this.roast = roast;
        this.roastScore = roastScore;
        this.idea = idea;
    }

    //getters / setters


    public Long getId() {
        return id;
    }

    public String getRoast() {
        return roast;
    }

    public void setRoast(String roast) {
        this.roast = roast;
    }

    public double getRoastScore() {
        return roastScore;
    }

    public void setRoastScore(double roastScore) {
        this.roastScore = roastScore;
    }

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }
}
