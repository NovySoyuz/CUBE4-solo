package com.example.demo1.domain.model;

public class Site {
    private int id;
    private String city;

    public Site() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    // Redefinission de toString car de base Java utilise toString() pour afficher des objets
    @Override
    public String toString() {
        return city;
    }
}
