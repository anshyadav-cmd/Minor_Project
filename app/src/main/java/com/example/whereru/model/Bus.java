package com.example.whereru.model;

public class Bus {

    private int busNumber ;
    private String busDriverName;
    private String busDriverPhone;

    public Bus(int busNumber, String busDriverName, String busDriverPhone) {
        this.busNumber = busNumber;
        this.busDriverName = busDriverName;
        this.busDriverPhone = busDriverPhone;
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

    public void setBusDriverPhone(String busDriverPhone) {
        this.busDriverPhone = busDriverPhone;
    }
}
