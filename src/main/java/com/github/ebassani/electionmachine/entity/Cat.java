package com.github.ebassani.electionmachine.entity;

import java.io.Serializable;

public class Cat implements Serializable {

    private String breed;
    private String color;

    public Cat() {
        breed = "siam";
        color = null;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
