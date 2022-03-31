package com.example.whereru.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bus implements Serializable {

    private int busNumber ;
    private String busDriverName;
    private String busDriverPhone;
    private List<BusStop> mStopList;

    public Bus(int busNumber, String busDriverName, String busDriverPhone , ArrayList<BusStop> stops) {
        this.busNumber = busNumber;
        this.busDriverName = busDriverName;
        this.busDriverPhone = busDriverPhone;
        this.mStopList = stops;
    }

    public int getBusNumber() {
        return busNumber;
    }

    public String getBusDriverName() {
        return busDriverName;
    }

    public String getBusDriverPhone() {
        return busDriverPhone;
    }

    public List<BusStop> getStopList() {
        return mStopList;
    }


}
