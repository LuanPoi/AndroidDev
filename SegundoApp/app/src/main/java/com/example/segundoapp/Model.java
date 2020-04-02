package com.example.segundoapp;

public class Model {
    private static final Model instance = new Model();

    private Model(){

    }

    static public Model getInstance(){
        return instance;
    }

    String text = "";
}
