package org.example.aiprojekttest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Roast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String input;
    private String answer;

    public Roast(String input, String answer) {
        this.input = input;
        this.answer = answer;
    }

    public Roast() {}

    public String getInput() {
        return input;
    }

    public String getAnswer() {
        return answer;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
