package com.example.application.domain.model;

public class Services {
    private int id;
    private String name;

    public Services() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString () {
        return name;
    }
}
