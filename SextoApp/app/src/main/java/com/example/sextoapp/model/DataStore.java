package com.example.sextoapp.model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataStore {
    private static DataStore instance = null;
    private boolean nameSortingSwitch = false;
    private boolean populationSortingSwitch = false;

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
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void addCity(City city){
        long returnResult = database.createCityInDatabase(city);
        if(returnResult > 0){
            city.setId(returnResult);
            cities.add(0, city);
        }else{
            Toast.makeText(context, "addCity Problem", Toast.LENGTH_LONG).show();
        }
    }
    public void editCity(City city, int position){
        int count = database.updateCityFromDatabase(city);
        if(count > 0){
            cities.set(position, city);
            Toast.makeText(context, "City updated", Toast.LENGTH_SHORT);
        }
    }
    public void removeCity(City city){
        int count = database.removeCityFromDatabase(city);
        if(count > 0){
            cities.remove(city);
        }
    }
    public int getCityListSize(){
        return cities.size();
    }

    public void sortCitiesByName(){
        nameSortingSwitch = !nameSortingSwitch;
        List<City> sortedCities = this.cities;

        Collections.sort(sortedCities, new Comparator<City>() {
            @Override
            public int compare(City city1, City city2)
            {
                if(nameSortingSwitch){
                    return  city1.getName().compareTo(city2.getName());
                }else{
                    return  city2.getName().compareTo(city1.getName());
                }
            }
        });

        this.cities = sortedCities;
    }

    public void sortCitiesByPopulation(){
        populationSortingSwitch = !populationSortingSwitch;
        List<City> sortedCities = this.cities;

        Collections.sort(sortedCities, new Comparator<City>() {
            @Override
            public int compare(City city1, City city2)
            {
                if(populationSortingSwitch){
                    return  city1.getPopulation() - (city2.getPopulation());
                }else{
                    return  city2.getPopulation() - (city1.getPopulation());
                }
            }
        });

        this.cities = sortedCities;
    }
}
