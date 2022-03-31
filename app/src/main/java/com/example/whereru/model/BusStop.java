package com.example.whereru.model;

public class BusStop {
    private String mTime;
    private String address;

    public BusStop(String time, String address) {
        mTime = time;
        this.address = address;
    }

    public String getTime() {
        return mTime;
    }

    public String getAddress() {
        return address;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
