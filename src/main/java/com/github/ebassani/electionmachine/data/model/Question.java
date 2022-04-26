package com.github.ebassani.electionmachine.data.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String question;

    public Question(){
        super();
    }
    public Question(int id, String question){
        super();
        this.id=id;
        this.question=question;
    }
    public Question(String question){
        super();
        this.question=question;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }
}
