package com.github.ebassani.electionmachine;

public class Question {
    int id;
    String question;

    public Question(int id, String question){
        this.id=id;
        this.question=question;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }
}
