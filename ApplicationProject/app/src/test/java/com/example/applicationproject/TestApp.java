package com.example.applicationproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;

import static org.junit.Assert.*;


public class TestApp{
    @Test



    public void welcomeTest(){
        Welcome w = new Welcome();
        if (w.identify("1") == 1){
            System.out.println("Successful for identify(1)");
        }
        if (w.identify("2") == 1){
            System.out.println("Successful for identify(2)");
        }

    }

    @Test
    public void userTest(){
        User u = new User();
        u.setPassword("5T5ptQ");
        u.setUsername("admin");
        u.setName("administrator");
        u.setIdentifier(1);

        System.out.println("Password = "+u.getPassword()+", Username = "+u.getUsername()+", Name = "+u.getName()+", Identifier = "+u.getIdentifier());


    }

    @Test
    public void loginPasswordTest(){
        Login.setPassword("This is the password");
        System.out.println(Login.password);
    }

    @Test
    public void loginUsernameTest(){
        Login.setUsername("This is the username");
        System.out.println(Login.username);
    }

    @Test
    public void loginNameTest(){
        Login.setName("This is the name");
        System.out.println(Login.name);
    }





}
