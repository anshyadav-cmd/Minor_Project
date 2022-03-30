package com.example.whereru.model;

public class BusStop {
    private String mDate;
    private String address;

    public BusStop(String date, String address) {
        mDate = date;
        this.address = address;
    }

    public String getDate() {
        return mDate;
    }

    public String getAddress() {
        return address;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
