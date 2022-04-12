package com.example.whereru;

import androidx.annotation.NonNull;

public class User {
    private String userID;
    private String name;
    private String email;
    private String phone;
    private boolean isDriver;

    public User(String name, String email, String phone, String userID, boolean isDriver) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.userID = userID;
        this.isDriver = isDriver;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isDriver() {
        return isDriver;
    }
    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    @NonNull
    @Override
    public String toString() {
        return  userID + " " + name + " " + isDriver ;
    }
}
