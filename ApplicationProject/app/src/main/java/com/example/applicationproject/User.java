/* *********************************************
USER CLASS WITH CONSTRUCTOR AND GETTERS/SETTERS
 ************************************************/

package com.example.applicationproject;

public class User {
    private String name;
    private String username;
    private String password;
    private int identifier;


    public User() {
    }


    //Getters/Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}

