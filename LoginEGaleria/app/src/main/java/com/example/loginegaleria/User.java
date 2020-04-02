package com.example.loginegaleria;

public class User {
    private String username;
    private Long password;

    private User(){

    }

    public User(String username, Long password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public Long getPassword(){
        return this.password;
    }
}
