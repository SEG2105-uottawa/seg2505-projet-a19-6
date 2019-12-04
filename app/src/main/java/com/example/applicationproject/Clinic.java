package com.example.applicationproject;

import java.util.ArrayList;

public class Clinic {
    private String phoneNum, address, name, insuranceType, payMethod, username;



    public Clinic(){

    }

    public Clinic(String phoneNum, String address, String name, String insuranceType, String payMethod, String username) {
        this.phoneNum = phoneNum;
        this.address = address;
        this.name = name;
        this.insuranceType = insuranceType;
        this.payMethod = payMethod;

    }



    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public  String getUsername(){
        return username;
    }


    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public void setUsername(String username){
        this.username = username;
    }


}
