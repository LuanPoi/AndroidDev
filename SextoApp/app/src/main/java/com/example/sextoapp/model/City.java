package com.example.sextoapp.model;

import androidx.annotation.NonNull;

public class City {
    private long id;
    private String name;
    private int population;

    public City(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @NonNull
    @Override
    public String toString() {
        return "City{" + "name=" + name + ",people=" + population + "}";
    }
}
