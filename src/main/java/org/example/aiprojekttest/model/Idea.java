package org.example.aiprojekttest.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idea;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Roast> responses;


    public Idea() {}

    public Idea(Long id, String idea, User user, List<Roast> responses) {
        this.id = id;
        this.idea = idea;
        this.user = user;
        this.responses = responses;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Roast> getResponses() {
        return responses;
    }

    public void setResponses(List<Roast> responses) {
        this.responses = responses;
    }
}
