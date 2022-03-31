package com.example.whereru.model;

import java.util.ArrayList;
import java.util.List;

public class Bus {

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

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }

    public String getBusDriverName() {
        return busDriverName;
    }

    public void setBusDriverName(String busDriverName) {
        this.busDriverName = busDriverName;
    }

    public String getBusDriverPhone() {
        return busDriverPhone;
    }

    public List<BusStop> getStopList() {
        return mStopList;
    }

    public void setStopList(List<BusStop> stopList) {
        mStopList = stopList;
    }

    public void setBusDriverPhone(String busDriverPhone) {
        this.busDriverPhone = busDriverPhone;
    }
}
