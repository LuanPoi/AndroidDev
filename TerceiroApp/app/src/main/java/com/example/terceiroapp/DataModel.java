package com.example.terceiroapp;

import java.util.ArrayList;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){

    }
    public static DataModel getInstance(){
        return instance;
    }

    public ArrayList<String> lista = new ArrayList<String>();
}
