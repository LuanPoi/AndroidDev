package com.example.loginegaleria;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final Model instance = new Model();

    private Model(){

    }

    static public Model getInstance(){
        return instance;
    }

    User loggedUser = null;

    List<User> registeredUsers = new ArrayList<User>();

    public boolean login(String login, Long password){
        for (User user: registeredUsers){
            if(user.getUsername().contentEquals(login)){
                if(user.getPassword().compareTo(password) == 0){
                    return true;
                }
            }
        }
        return false;
    }
}