package com.example.sextoapp.model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataStore {
    private static DataStore instance = null;

    private DataStore(){}
    public static DataStore getInstance(){
        if(instance == null)
            instance = new DataStore();

        return instance;
    }

    private CityDatabase database;
    private List<City> cities;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
        database = new CityDatabase(context);
        cities = database.retrieveCitiesFromDatabase();
//        addCity(new City("Curitiba", 2000000));
//        addCity(new City("São Paulo", 12000000));
//        addCity(new City("Rio de Janeiro", 8000000));
    }

    public List<City> getCities() {
        return cities;
    }
    public void addCity(City city){
        if(database.createCityInDatabase(city) > 0){
            cities.add(city);
        }else{
            Toast.makeText(context, "addCity Problem", Toast.LENGTH_LONG).show();
        }
    }
    public void editCity(City city, int position){
        int count = database.updateCityFromDatabase(city);
        if(count > 0){
            cities.set(position, city);
        }
    }
    public void removeCity(int position){
        int count = database.removeCityFromDatabase(cities.get(position));
        if(count > 0){
            cities.remove(position);
        }
    }
    public int getCityListSize(){
        return cities.size();
    }

    public void sortCitiesByName(){
        List<City> sortedCities = this.cities;

        Collections.sort(sortedCities, new Comparator<City>() {
            @Override
            public int compare(City city2, City city1)
            {
                return  city1.getName().compareTo(city2.getName());
            }
        });

        this.cities = sortedCities;
    }

    public void sortCitiesByPopulation(){
        List<City> sortedCities = this.cities;

        sortedCities.sort(Comparator.comparing(City::getPopulation));

        this.cities = sortedCities;
    }

}
